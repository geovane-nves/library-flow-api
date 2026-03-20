package com.Project.LibraryFlow.loan.repositories;

import com.Project.LibraryFlow.loan.entities.Loan;
import com.Project.LibraryFlow.loan.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {

    List<Loan> findByStatusAndDueDateBefore(LoanStatus status, Instant date);

    List<Loan> findByUserId(UUID userId);

    boolean existsByUserIdAndCopy_Book_Id(UUID userId, UUID bookId);

}
