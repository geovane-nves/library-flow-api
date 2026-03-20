package com.Project.LibraryFlow.fine.dtos;

import com.Project.LibraryFlow.fine.entities.Fine;
import com.Project.LibraryFlow.fine.enums.FineStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record FineResponseDTO(
        UUID id,
        UUID loanId,
        UUID userId,
        BigDecimal amount,
        Integer daysLate,
        FineStatus status,
        Instant createdAt
) {
    public static FineResponseDTO fromEntity(Fine fine) {
        return new FineResponseDTO(
                fine.getId(),
                fine.getLoan().getId(),
                fine.getLoan().getUser().getId(),
                fine.getAmount(),
                fine.getDaysLate(),
                fine.getStatus(),
                fine.getCreatedAt()
        );
    }
}