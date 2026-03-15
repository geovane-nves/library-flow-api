package com.Project.LibraryFlow.author.dtos;

import com.Project.LibraryFlow.author.entities.Author;

import java.util.UUID;

public record AuthorResponseDTO(
        UUID id,
        String name,
        String biography
) {
    public static AuthorResponseDTO fromEntity(Author author) {
        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getBiography()
        );
    }
}
