package com.Project.LibraryFlow.author.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequestDTO (

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "biography is required")
        String biography
){}