package com.Project.LibraryFlow.loan.dtos;

import com.Project.LibraryFlow.loan.entities.Loan;
import com.Project.LibraryFlow.loan.enums.LoanStatus;
import com.Project.LibraryFlow.fine.entities.Fine;
import com.Project.LibraryFlow.fine.enums.FineStatus;

import java.time.Instant;
import java.util.UUID;

public record LoanResponseDTO(
        UUID id,
        UUID userId,
        UUID copyId,
        Instant loanDate,
        Instant dueDate,
        Instant returnedAt,
        LoanStatus status,
        boolean hasPendingFine,
        Double fineAmount
) {
    public static LoanResponseDTO fromEntity(Loan loan, Fine fine) {
        return new LoanResponseDTO(
                loan.getId(),
                loan.getUser().getId(),
                loan.getCopy().getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnedAt(),
                loan.getStatus(),
                fine != null && fine.getStatus() == FineStatus.PENDING,
                fine != null ? fine.getAmount().doubleValue() : 0.0
        );
    }

    public static LoanResponseDTO fromEntity(Loan loan) {
        return new LoanResponseDTO(
                loan.getId(),
                loan.getUser().getId(),
                loan.getCopy().getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnedAt(),
                loan.getStatus(),
                false,
                0.0
        );
    }
}