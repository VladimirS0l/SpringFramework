package ru.solarev.lesson2.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.solarev.lesson2.exception.NotFoundResourceException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(NotFoundResourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerResourceNotFound(NotFoundResourceException e) {
        return e.getMessage();
    }
}
