package com.Project.LibraryFlow.reservation.entities;

import com.Project.LibraryFlow.book.entities.Book;
import com.Project.LibraryFlow.user.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
public class Reservation {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant createdAt;

    public Reservation() {
    }

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
        this.createdAt = Instant.now();
    }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Book getBook() { return book; }

    public void setBook(Book book) { this.book = book; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public UUID getId() { return id; }
}
