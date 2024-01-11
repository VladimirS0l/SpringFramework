package ru.solarev.lesson2.exception;

public class NotFoundResourceException extends RuntimeException {
    public NotFoundResourceException(String msg) {
        super(msg);
    }
}
