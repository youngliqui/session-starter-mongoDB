package ru.clevertec.session_starter.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.session_starter.dto.SessionCreateDTO;
import ru.clevertec.session_starter.entity.Session;
import ru.clevertec.session_starter.service.SessionService;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody SessionCreateDTO sessionCreateDTO) {
        Session createdSession = sessionService.createSession(sessionCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }

    @GetMapping("/{login}")
    public ResponseEntity<Session> getSessionByLogin(@PathVariable("login") String login) {
        Session foundSession = sessionService.findSessionByLogin(login);

        return ResponseEntity.ok(foundSession);
    }
}
