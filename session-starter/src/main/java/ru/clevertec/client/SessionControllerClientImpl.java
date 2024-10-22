package ru.clevertec.client;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.property.SessionProperties;
import ru.clevertec.session_starter.dto.SessionCreateDTO;
import ru.clevertec.session_starter.entity.Session;

@Slf4j
@RequiredArgsConstructor
public class SessionControllerClientImpl implements SessionControllerClient {
    private final RestTemplate restTemplate;
    private final SessionProperties sessionProperties;

    @PostConstruct
    void init() {
        log.info("Session client baseURL: {}", sessionProperties.getBaseUrl());
    }

    @Override
    public Session getSessionByLogin(String login) {
        return restTemplate.getForObject(sessionProperties.getBaseUrl() + "/" + login, Session.class);
    }

    @Override
    public Session createSession(String login) {
        return restTemplate.postForObject(sessionProperties.getBaseUrl(), new SessionCreateDTO(login), Session.class);
    }
}
