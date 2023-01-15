package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Map<String, User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        return userService.create(user);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        return userService.update(user);
    }
}
