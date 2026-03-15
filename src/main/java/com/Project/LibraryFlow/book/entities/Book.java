package com.Project.LibraryFlow.book.entities;

import com.Project.LibraryFlow.author.entities.Author;
import com.Project.LibraryFlow.category.entities.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
public class Book implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "book_id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Isbn is required")
    private String isbn;

    @NotNull(message = "Publication year is required")
    private Integer publicationYear;

    @NotBlank(message = "Description is required")
    private String description;

    private Instant createdAt;

    public Book() {
    }

    public Book(Author author, Category category, String title, String isbn, Integer publicationYear, String description) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.description = description;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
