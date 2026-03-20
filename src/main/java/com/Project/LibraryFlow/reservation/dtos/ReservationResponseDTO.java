package com.Project.LibraryFlow.reservation.dtos;

import com.Project.LibraryFlow.reservation.entities.Reservation;

import java.time.Instant;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,
        UUID userId,
        UUID bookId,
        Instant createdAt
) {
    public static ReservationResponseDTO toDTO(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getBook().getId(),
                reservation.getCreatedAt()
        );
    }
}

