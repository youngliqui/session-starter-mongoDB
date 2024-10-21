package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.clevertec.session_starter.entity.Session;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {
    private String login;
    private Session session;
}
