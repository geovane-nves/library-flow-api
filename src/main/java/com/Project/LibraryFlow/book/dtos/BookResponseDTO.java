package com.Project.LibraryFlow.book.dtos;

import com.Project.LibraryFlow.book.entities.Book;

import java.util.UUID;

public record BookResponseDTO(
        UUID id,
        String author,
        String category,
        String title,
        String isbn,
        Integer publicationYear,
        String description
) {
    public static BookResponseDTO fromEntity(Book book) {
        return new BookResponseDTO(
                book.getId(),
                book.getAuthor().getName(),
                book.getCategory().getName(),
                book.getTitle(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getDescription()
        );
    }
}
