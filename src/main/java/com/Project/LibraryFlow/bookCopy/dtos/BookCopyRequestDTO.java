package com.Project.LibraryFlow.bookCopy.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BookCopyRequestDTO (

        @NotNull(message = "BookId is required")
        UUID bookId,

        @NotNull(message = "Shelf location is required")
        String shelfLocation
){}
