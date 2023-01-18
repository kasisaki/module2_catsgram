package ru.yandex.practicum.catsgram.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Post {

    private Integer id;
    private final String author; // автор
    private final LocalDateTime creationDate = LocalDateTime.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии

    @Autowired
    public Post(Integer id, String author, String description, String photoUrl) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}