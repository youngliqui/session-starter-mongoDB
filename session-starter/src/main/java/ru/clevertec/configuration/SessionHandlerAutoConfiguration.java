package ru.clevertec.configuration;

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
import ru.clevertec.service.SessionService;
import ru.clevertec.service.SessionServiceImpl;

import java.util.List;

@AutoConfiguration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@EnableConfigurationProperties(value = {PropertyBlackListProvider.class})
@ConditionalOnProperty(value = "session.handler.enable", havingValue = "true", matchIfMissing = true)
public class SessionHandlerAutoConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SessionControllerClient sessionControllerClient() {
        return new SessionControllerClientImpl(restTemplate());
    }

    @Bean
    public SessionService sessionService() {
        return new SessionServiceImpl(sessionControllerClient());
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
