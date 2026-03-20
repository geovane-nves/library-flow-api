package com.Project.LibraryFlow.review.entities;

import com.Project.LibraryFlow.book.entities.Book;
import com.Project.LibraryFlow.user.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt = Instant.now();

    public Review() {}

    public Review(User user, Book book, Integer rating, String comment) {
        this.user = user;
        this.book = book;
        this.rating = rating;
        this.comment = comment;
    }

    public UUID getId() { return id; }

    public User getUser() { return user; }

    public Book getBook() { return book; }

    public Integer getRating() { return rating; }

    public String getComment() { return comment; }

    public Instant getCreatedAt() { return createdAt; }
}