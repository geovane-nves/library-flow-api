package com.Project.LibraryFlow.review.repositories;

import com.Project.LibraryFlow.review.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {

    List<Review> findByBookId(UUID bookId);

}