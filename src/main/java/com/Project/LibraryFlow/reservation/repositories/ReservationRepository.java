package com.Project.LibraryFlow.reservation.repositories;

import com.Project.LibraryFlow.reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findByBookIdOrderByCreatedAtAsc(UUID bookId);

    boolean existsByUserIdAndBookId(UUID userId, UUID bookId);
}