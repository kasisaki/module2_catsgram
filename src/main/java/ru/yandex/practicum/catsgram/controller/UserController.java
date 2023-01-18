package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/users/{userEmail}")
    @ResponseBody
    public ResponseEntity<User> findById(@PathVariable String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException(String.format("User %s not found", userEmail));
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        if (userService.create(user) == null) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        if (userService.update(user) == null) {
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
