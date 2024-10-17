package ru.clevertec.session_starter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.clevertec.session_starter.dto.SessionCreateDTO;
import ru.clevertec.session_starter.entity.Session;
import ru.clevertec.session_starter.exception.SessionNotFoundException;
import ru.clevertec.session_starter.repository.SessionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    @Override
    public Session createSession(SessionCreateDTO sessionCreateDTO) {
        Session cratedSession = Session.builder()
                .id(UUID.randomUUID())
                .login(sessionCreateDTO.getLogin())
                .openedAt(LocalDateTime.now())
                .build();

        return sessionRepository.save(cratedSession);
    }

    @Override
    public Session findSessionByLogin(String login) {
        return sessionRepository.findByLogin(login)
                .orElseThrow(() ->
                        new SessionNotFoundException("Session with login = " + login + " was not found"));
    }

    @Override
    @Scheduled(cron = "${scheduler.cron.clean-expired-sessions}")
    public void cleanUpExpiredSessions() {
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
        sessionRepository.deleteAll(
                sessionRepository.findAll()
                        .stream()
                        .filter(session -> session.getOpenedAt().isBefore(endOfDay))
                        .toList()
        );
    }
}
