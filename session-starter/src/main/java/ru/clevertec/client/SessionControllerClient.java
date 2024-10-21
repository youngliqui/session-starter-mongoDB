package ru.clevertec.client;

import ru.clevertec.session_starter.entity.Session;

public interface SessionControllerClient {
    Session getSessionByLogin(String login);

    Session createSession(String login);
}
