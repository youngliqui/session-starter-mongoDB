package ru.clevertec.property;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "session.login")
public class PropertyBlackListProvider implements BlackListProvider {
    private Set<String> blacklist = new HashSet<>();

    @PostConstruct
    public void init() {
        log.info("Initializing blacklist with properties: {}", this.blacklist);
    }
}