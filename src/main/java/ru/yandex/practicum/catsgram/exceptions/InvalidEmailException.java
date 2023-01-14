package ru.yandex.practicum.catsgram.exceptions;

public class InvalidEmailException extends Throwable {
    public InvalidEmailException() {
        System.out.println("Invalid Email");
    }

    @Override
    public String getMessage() {
        return "Invalid Email";
    }
}
