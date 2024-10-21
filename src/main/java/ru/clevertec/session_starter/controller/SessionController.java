package ru.clevertec.session_starter.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.session_starter.dto.SessionCreateDTO;
import ru.clevertec.session_starter.entity.Session;
import ru.clevertec.session_starter.service.SessionService;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@Slf4j
public class SessionController {
    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody SessionCreateDTO sessionCreateDTO) {
        Session createdSession = sessionService.createSession(sessionCreateDTO);
        log.info("Created session: {}", createdSession);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }

    @GetMapping("/{login}")
    public ResponseEntity<Session> getSessionByLogin(@PathVariable("login") String login) {
        Session foundSession = sessionService.findSessionByLogin(login);
        log.info("Found session: {}", foundSession);

        return ResponseEntity.ok(foundSession);
    }
}
