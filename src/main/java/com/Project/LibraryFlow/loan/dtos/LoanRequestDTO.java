package com.Project.LibraryFlow.loan.dtos;

import com.Project.LibraryFlow.bookCopy.entities.BookCopy;
import com.Project.LibraryFlow.user.entities.User;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LoanRequestDTO(

        @NotNull(message = "User is required")
        UUID user,

        @NotNull(message = "Book copy is required")
        UUID copy
){}
