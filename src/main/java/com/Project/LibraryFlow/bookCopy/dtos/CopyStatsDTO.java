package com.Project.LibraryFlow.bookCopy.dtos;

import com.Project.LibraryFlow.bookCopy.enums.CopyStatus;
import com.Project.LibraryFlow.bookCopy.repository.BookCopyRepository;

import java.util.UUID;

public record CopyStatsDTO(
        long totalCopies,
        long availableCopies,
        long borrowedCopies,
        long reservedCopies,
        long lostCopies
) {
    public static CopyStatsDTO fromRepository(BookCopyRepository repository, UUID bookId) {
        long total = repository.countByBookId(bookId);
        long available = repository.countByBookIdAndStatus(bookId, CopyStatus.AVAILABLE);
        long borrowed = repository.countByBookIdAndStatus(bookId, CopyStatus.BORROWED);
        long reserved = repository.countByBookIdAndStatus(bookId, CopyStatus.RESERVED);
        long lost = repository.countByBookIdAndStatus(bookId, CopyStatus.LOST);
        return new CopyStatsDTO(total, available, borrowed, reserved, lost);
    }
}