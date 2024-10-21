package ru.clevertec.client;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ru.clevertec.session_starter.dto.SessionCreateDTO;
import ru.clevertec.session_starter.entity.Session;

@Slf4j
@RequiredArgsConstructor
public class SessionControllerClientImpl implements SessionControllerClient {
    private final RestTemplate restTemplate;

    @Value("${session.controller.baseUrl}")
    private String baseUrl;

    @PostConstruct
    void init() {
        log.info("Session client baseURL: {}", baseUrl);
    }

    @Override
    public Session getSessionByLogin(String login) {
        return restTemplate.getForObject(baseUrl + "/" + login, Session.class);
    }

    @Override
    public Session createSession(String login) {
        return restTemplate.postForObject(baseUrl, new SessionCreateDTO(login), Session.class);
    }
}
