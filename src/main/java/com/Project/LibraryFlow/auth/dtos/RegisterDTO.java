package com.Project.LibraryFlow.auth.dtos;

import com.Project.LibraryFlow.user.entities.User;
import com.Project.LibraryFlow.user.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotNull(message = "Type is required")
        UserType userType
) {
    public User toEntity(String encodedPassword) {
        return new User(name, email, encodedPassword, userType);
    }
}