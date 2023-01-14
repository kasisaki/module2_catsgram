package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.HashSet;
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public HashSet<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @ResponseBody
    public User create(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        return userService.create(user);
    }

    @PutMapping
    @ResponseBody
    public User update(@RequestBody User user) throws InvalidEmailException {
        return userService.update(user);
    }
}
