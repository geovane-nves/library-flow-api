package com.Project.LibraryFlow.loan.dtos;

import com.Project.LibraryFlow.loan.entities.Loan;
import com.Project.LibraryFlow.loan.enums.LoanStatus;

import java.time.Instant;
import java.util.UUID;

public record LoanResponseDTO(
        UUID id,
        UUID userId,
        UUID copyId,
        Instant loanDate,
        Instant dueDate,
        Instant returnedAt,
        LoanStatus status
){
    public static LoanResponseDTO fromEntity(Loan loan) {
        return new LoanResponseDTO(
                loan.getId(),
                loan.getUser().getId(),
                loan.getCopy().getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnedAt(),
                loan.getStatus()
        );
    }
}
