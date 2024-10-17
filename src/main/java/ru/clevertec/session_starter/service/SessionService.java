package ru.clevertec.session_starter.service;

import ru.clevertec.session_starter.dto.SessionCreateDTO;
import ru.clevertec.session_starter.entity.Session;

public interface SessionService {

    Session createSession(SessionCreateDTO sessionCreateDTO);

    Session findSessionByLogin(String login);

    void cleanUpExpiredSessions();
}
