package com.Project.LibraryFlow.bookCopy.entities;

import com.Project.LibraryFlow.book.entities.Book;
import com.Project.LibraryFlow.bookCopy.enums.CopyStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;
import java.util.UUID;

@Entity
public class BookCopy {

    @Id
    @UuidGenerator
    @Column(name = "bookCopy_id", unique = true, nullable = false)
    private UUID id;

    @ManyToOne
    private Book book;

    private CopyStatus status;

    private String shelfLocation;

    public BookCopy() {
    }

    public BookCopy(Book book, String shelfLocation) {
        this.book = book;
        this.status = CopyStatus.AVAILABLE;
        this.shelfLocation = shelfLocation;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public CopyStatus getStatus() {
        return status;
    }

    public void setStatus(CopyStatus status) {
        this.status = status;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return Objects.equals(id, bookCopy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
