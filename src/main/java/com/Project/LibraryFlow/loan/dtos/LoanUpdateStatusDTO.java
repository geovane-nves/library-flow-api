package com.Project.LibraryFlow.loan.dtos;

import com.Project.LibraryFlow.loan.enums.LoanStatus;

public record LoanUpdateStatusDTO(
        LoanStatus status
) {}