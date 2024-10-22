package ru.clevertec.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import ru.clevertec.annotation.SessionHandler;
import ru.clevertec.dto.SessionRequest;
import ru.clevertec.exception.LoginBlacklistedException;
import ru.clevertec.exception.SessionRequestNotFoundException;
import ru.clevertec.property.BlackListProvider;
import ru.clevertec.session_starter.entity.Session;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
public class SessionHandlerInterceptor implements MethodInterceptor {
    private final Object bean;
    private final BeanFactory beanFactory;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if (method.isAnnotationPresent(SessionHandler.class)) {
            SessionHandler annotation = method.getAnnotation(SessionHandler.class);
            SessionRequest sessionRequest = getSessionRequest(args);
            String login = sessionRequest.getLogin();

            validateLoginAgainstBlacklist(annotation.blacklist(), login);

            Session session = getSessionForLogin(login);
            sessionRequest.setSession(session);
        }

        return proxy.invoke(bean, args);
    }

    private SessionRequest getSessionRequest(Object[] args) {
        return Arrays.stream(args)
                .filter(SessionRequest.class::isInstance)
                .map(SessionRequest.class::cast)
                .findFirst()
                .orElseThrow(() ->
                        new SessionRequestNotFoundException("No argument of type SessionRequest found"));
    }

    private Session getSessionForLogin(String login) {
        return beanFactory.getBean(SessionServiceImpl.class).getSession(login);
    }

    private void validateLoginAgainstBlacklist(
            Class<? extends BlackListProvider>[] blacklistProviders,
            String login
    ) {
        Set<String> combinedBlacklist = Arrays.stream(blacklistProviders)
                .map(beanFactory::getBean)
                .flatMap(provider -> provider.getBlacklist().stream())
                .collect(Collectors.toSet());

        if (combinedBlacklist.contains(login)) {
            throw new LoginBlacklistedException("Login is blacklisted: " + login);
        }
    }
}
