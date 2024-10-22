package ru.clevertec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SessionRequestNotFoundException extends RuntimeException {
    public SessionRequestNotFoundException(String message) {
        super(message);
    }
}