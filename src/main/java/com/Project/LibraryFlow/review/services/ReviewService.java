package com.Project.LibraryFlow.review.services;

import com.Project.LibraryFlow.book.entities.Book;
import com.Project.LibraryFlow.book.repositories.BookRepository;
import com.Project.LibraryFlow.loan.repositories.LoanRepository;
import com.Project.LibraryFlow.review.dtos.ReviewRequestDTO;
import com.Project.LibraryFlow.review.entities.Review;
import com.Project.LibraryFlow.review.repositories.ReviewRepository;
import com.Project.LibraryFlow.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    public Review create(ReviewRequestDTO dto, User user) {

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        boolean hasLoan = loanRepository.existsByUserIdAndCopy_Book_Id(user.getId(), book.getId());

        if (!hasLoan) {throw new RuntimeException("You can't rate a book you haven't rented");}
        Review review = new Review(
                user,
                book,
                dto.rating(),
                dto.comment()
        );

        return reviewRepository.save(review);
    }

    public List<Review> getByBook(UUID bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public void delete(UUID reviewId, User user) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow();

        if (!review.getUser().getId().equals(user.getId())
                && !user.getUserType().name().equals("ADMIN")) {
            throw new RuntimeException("Without permission");
        }
        reviewRepository.delete(review);
    }
}