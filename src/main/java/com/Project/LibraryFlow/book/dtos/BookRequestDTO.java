package com.Project.LibraryFlow.book.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BookRequestDTO(

        @NotNull(message = "AuthorId is required")
        UUID authorId,

        @NotNull(message = "CategoryId is required")
        UUID categoryId,

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "ISBN is required")
        String isbn,

        @NotNull(message = "Publication Year is required")
        Integer publicationYear,

        @NotBlank(message = "Description is required")
        String description
){}
