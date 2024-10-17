package ru.clevertec.session_starter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.clevertec.session_starter.entity.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends MongoRepository<Session, UUID> {
    Optional<Session> findByLogin(String login);
}
