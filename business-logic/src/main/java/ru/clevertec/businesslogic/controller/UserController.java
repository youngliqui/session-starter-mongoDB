package ru.clevertec.businesslogic.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.annotation.SessionHandler;
import ru.clevertec.businesslogic.dto.CreateUserDTO;
import ru.clevertec.businesslogic.dto.UserChangeEmailDTO;
import ru.clevertec.businesslogic.entity.User;
import ru.clevertec.businesslogic.service.UserService;
import ru.clevertec.property.PropertyBlackListProvider;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @SessionHandler(blacklist = PropertyBlackListProvider.class)
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO createUserDTO) {
        User createdUser = userService.createUser(createUserDTO);
        log.info("Session: {}; Created User: {}", createUserDTO.getSession(), createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username") String username) {
        User foundUser = userService.getUserByUsername(username);
        log.info("Found User: {}", foundUser);

        return ResponseEntity.ok(foundUser);
    }

    @PatchMapping("/{id}/email")
    @SessionHandler(blacklist = PropertyBlackListProvider.class)
    public ResponseEntity<User> changeEmail(
            @PathVariable("id") Long userId,
            @RequestBody UserChangeEmailDTO userChangeEmailDTO
    ) {
        User changedUser = userService.changeEmail(userId, userChangeEmailDTO.getEmail());
        log.info("Session: {}; Changed User: {}", userChangeEmailDTO.getSession(), changedUser);

        return ResponseEntity.ok(changedUser);
    }
}
