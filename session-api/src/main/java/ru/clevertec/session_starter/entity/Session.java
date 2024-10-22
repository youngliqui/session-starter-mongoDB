package ru.clevertec.session_starter.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@Document(collection = "sessions")
public class Session {
    @Id
    private UUID id;

    @Indexed(unique = true)
    private String login;

    private LocalDateTime openedAt;
}