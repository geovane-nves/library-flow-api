package com.Project.LibraryFlow.review.dtos;

import com.Project.LibraryFlow.review.entities.Review;

import java.time.Instant;
import java.util.UUID;

public record ReviewResponseDTO(

        UUID id,
        UUID userId,
        String userName,
        UUID bookId,
        String bookName,
        Integer rating,
        String comment,
        Instant createdAt
) {
    public static ReviewResponseDTO fromEntity(Review review) {
        return new ReviewResponseDTO(
                review.getId(),
                review.getUser().getId(),
                review.getUser().getName(),
                review.getBook().getId(),
                review.getBook().getTitle(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );
    }
}