package com.Project.LibraryFlow.bookCopy.dtos;

import com.Project.LibraryFlow.bookCopy.entities.BookCopy;
import com.Project.LibraryFlow.bookCopy.enums.CopyStatus;

import java.util.UUID;

public record BookCopyResponseDTO(
        UUID id,
        String book,
        String status,
        String shelfLocation
){
    public static BookCopyResponseDTO fromEntity(BookCopy copy) {
        return new BookCopyResponseDTO(
                copy.getId(),
                copy.getBook().getTitle(),
                copy.getStatus().name(),
                copy.getShelfLocation()
        );
    }
}
