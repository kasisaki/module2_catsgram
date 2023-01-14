package ru.yandex.practicum.catsgram.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
@Service
@Data
public class UserService {
    private final HashSet<User> users;

    public HashSet<User> findAll() {
        return users;
    }


    public User create(User user) throws UserAlreadyExistException, InvalidEmailException {
        if (users.contains(user)) {
            throw new UserAlreadyExistException();
        }
        if (user == null || !user.getEmail().contains("@")) {
            throw new InvalidEmailException();
        }
        users.add(user);
        return user;
    }

    public User update(User user) throws InvalidEmailException {
        if (!user.getEmail().contains("@")) {
            throw new InvalidEmailException();
        }
        if (users.contains(user)) {
            users.remove(user);
            users.add(user);
            return user;
        }
        users.add(user);
        return user;
    }
}
