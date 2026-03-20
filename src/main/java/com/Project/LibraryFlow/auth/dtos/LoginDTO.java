package com.Project.LibraryFlow.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {}