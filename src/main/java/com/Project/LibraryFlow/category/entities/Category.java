package com.Project.LibraryFlow.category.entities;

import com.Project.LibraryFlow.book.entities.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Category implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "category_id", nullable = false, unique = true)
    private UUID id;

    private String name;

    public Category() {
    }

    public Category(String name) { this.name = name; }

    public UUID getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }
}
