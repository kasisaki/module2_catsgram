package ru.yandex.practicum.catsgram.exceptions;

public class UserAlreadyExistException extends Throwable {
    public UserAlreadyExistException() {
        System.out.println("User exists");
    }

    @Override
    public String getMessage() {
        return "User Already Exist";
    }
}
