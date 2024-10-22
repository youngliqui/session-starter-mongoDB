package ru.clevertec.configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.bpp.SessionHandlerBeanPostProcessor;
import ru.clevertec.client.SessionControllerClient;
import ru.clevertec.client.SessionControllerClientImpl;
import ru.clevertec.listener.ApplicationStartListener;
import ru.clevertec.property.BlackListProvider;
import ru.clevertec.property.PropertyBlackListProvider;
import ru.clevertec.property.SessionProperties;
import ru.clevertec.service.SessionService;
import ru.clevertec.service.SessionServiceImpl;

import java.util.List;

@Slf4j
@AutoConfiguration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@EnableConfigurationProperties(value = {SessionProperties.class, PropertyBlackListProvider.class})
@ConditionalOnProperty(name = "session.handlerEnable", havingValue = "true", matchIfMissing = true)
public class SessionHandlerAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("SessionHandlerAutoConfiguration has been initialized and is active.");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SessionControllerClient sessionControllerClient(
            RestTemplate restTemplate, SessionProperties sessionProperties
    ) {
        return new SessionControllerClientImpl(restTemplate, sessionProperties);
    }

    @Bean
    public SessionService sessionService(SessionControllerClient sessionControllerClient) {
        return new SessionServiceImpl(sessionControllerClient);
    }

    @Bean
    public SessionHandlerBeanPostProcessor sessionHandlerBeanPostProcessor() {
        return new SessionHandlerBeanPostProcessor();
    }

    @Bean
    public ApplicationStartListener applicationStartListener(List<BlackListProvider> blackListProviders) {
        return new ApplicationStartListener(blackListProviders);
    }
}