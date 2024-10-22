package ru.clevertec.service;

import ru.clevertec.session_starter.entity.Session;

public interface SessionService {
    Session getSession(String login);
}
