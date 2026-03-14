package com.Project.LibraryFlow.author.dtos;

import com.Project.LibraryFlow.author.entities.Author;

import java.util.UUID;

public record AuthorResponseDTO(
        UUID id,
        String name,
        String biography
) {
    public AuthorResponseDTO (Author author) {
        this(
                author.getId(),
                author.getName(),
                author.getBiography()
        );
    }
}
