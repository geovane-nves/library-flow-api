package com.Project.LibraryFlow.reservation.services;

import com.Project.LibraryFlow.book.repositories.BookRepository;
import com.Project.LibraryFlow.bookCopy.enums.CopyStatus;
import com.Project.LibraryFlow.bookCopy.repository.BookCopyRepository;
import com.Project.LibraryFlow.reservation.dtos.ReservationResponseDTO;
import com.Project.LibraryFlow.reservation.entities.Reservation;
import com.Project.LibraryFlow.reservation.repositories.ReservationRepository;
import com.Project.LibraryFlow.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public ReservationResponseDTO reserveBook(UUID userId, UUID bookId) {

        boolean hasAvailable = bookCopyRepository
                .existsByBookIdAndStatus(bookId, CopyStatus.AVAILABLE);

        if (hasAvailable) {
            throw new RuntimeException("Book available, reservation not possible.");
        }

        boolean alreadyReserved = reservationRepository
                .existsByUserIdAndBookId(userId, bookId);

        if (alreadyReserved) {
            throw new RuntimeException("You're already in line for this book.");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(userRepository.findById(userId).orElseThrow());
        reservation.setBook(bookRepository.findById(bookId).orElseThrow());
        reservation.setCreatedAt(Instant.now());

        return ReservationResponseDTO.toDTO(reservationRepository.save(reservation));
    }

    public List<ReservationResponseDTO> getQueue(UUID bookId) {
        return reservationRepository.findByBookIdOrderByCreatedAtAsc(bookId)
                .stream()
                .map(ReservationResponseDTO::toDTO)
                .toList();
    }
}