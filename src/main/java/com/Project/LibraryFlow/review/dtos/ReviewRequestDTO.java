package com.Project.LibraryFlow.review.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReviewRequestDTO(

        @NotNull(message = "Book is required")
        UUID bookId,

        @NotNull(message = "Rating is required")
        @Min(0) @Max(5)
        Integer rating,

        String comment
) {}