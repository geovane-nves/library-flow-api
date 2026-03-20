package com.Project.LibraryFlow.review.controller;

import com.Project.LibraryFlow.review.dtos.ReviewRequestDTO;
import com.Project.LibraryFlow.review.dtos.ReviewResponseDTO;
import com.Project.LibraryFlow.review.entities.Review;
import com.Project.LibraryFlow.review.services.ReviewService;
import com.Project.LibraryFlow.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ReviewResponseDTO create(@RequestBody ReviewRequestDTO dto, @AuthenticationPrincipal User user) {
        Review review = service.create(dto, user);
        return ReviewResponseDTO.fromEntity(review);
    }

    @GetMapping("/book/{bookId}")
    public List<ReviewResponseDTO> getByBook(@PathVariable UUID bookId) {
        return service.getByBook(bookId)
                .stream()
                .map(ReviewResponseDTO::fromEntity)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id, @AuthenticationPrincipal User user) {
        service.delete(id, user);
    }
}