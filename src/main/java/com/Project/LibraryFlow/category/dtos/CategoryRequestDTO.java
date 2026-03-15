package com.Project.LibraryFlow.category.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Name is required")
        String name
) {}
