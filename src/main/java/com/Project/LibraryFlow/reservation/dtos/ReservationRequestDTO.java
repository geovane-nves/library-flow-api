package com.Project.LibraryFlow.reservation.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservationRequestDTO(

        @NotNull(message = "UserId is required")
        UUID userId,

        @NotNull(message = "BookId is required")
        UUID bookId
) {}