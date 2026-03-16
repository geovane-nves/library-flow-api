package com.Project.LibraryFlow.bookCopy.dtos;

import com.Project.LibraryFlow.bookCopy.entities.BookCopy;
import com.Project.LibraryFlow.bookCopy.enums.CopyStatus;

public record UpdateCopyRequestDTO(
        String shelfLocation,
        CopyStatus status
) {
    public void applyTo(BookCopy copy) {
        copy.setShelfLocation(shelfLocation);
        copy.setStatus(status);
    }
}