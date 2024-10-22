package ru.clevertec.property;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConfigurationProperties(prefix = "session")
@Getter
@Setter
public class SessionProperties {
    private String baseUrl;
    private boolean handlerEnable;

    @PostConstruct
    public void logProperties() {
        log.info("Session Properties Initialized:");
        log.info("Controller Base URL: {}", baseUrl);
        log.info("Handler Enabled: {}", handlerEnable);
    }
}
