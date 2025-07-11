package ru.danil.mvc.bookmanagerboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFound extends RuntimeException {
    public PersonNotFound(String message) {
        super(message);
    }
}
