package ru.clevertec.service;

import ru.clevertec.client.SessionControllerClient;
import ru.clevertec.session_starter.entity.Session;


public class SessionServiceImpl implements SessionService {
    private final SessionControllerClient sessionControllerClient;

    public SessionServiceImpl(SessionControllerClient sessionControllerClient) {
        this.sessionControllerClient = sessionControllerClient;
    }

    @Override
    public Session getSession(String login) {
        try {
            return sessionControllerClient.getSessionByLogin(login);
        } catch (Exception e) {
            return sessionControllerClient.createSession(login);
        }
    }
}