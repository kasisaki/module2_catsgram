package ru.yandex.practicum.catsgram.service;

import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public Map<String, User> findAll() {
        return users;
    }


    public ResponseEntity<User> create(User user) throws UserAlreadyExistException, InvalidEmailException {
        if (users.containsValue(user)) {
            throw new UserAlreadyExistException();
        }
        if (user == null || !user.getEmail().contains("@")) {
            throw new InvalidEmailException();
        }
        users.put(user.getEmail(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<User> update(User user) {
        if (users.containsKey(user.getEmail())) {
            users.put(user.getEmail(), user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }

    public User findUserByEmail(String email) {
        if (users.containsKey(email)) {
            return users.get(email);
        }
        return null;
    }
}
