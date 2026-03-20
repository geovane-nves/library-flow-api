package com.Project.LibraryFlow.fine.repositories;

import com.Project.LibraryFlow.fine.entities.Fine;
import com.Project.LibraryFlow.fine.enums.FineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FineRepository extends JpaRepository<Fine, UUID> {

    Optional<Fine> findByLoanId(UUID loanId);

    List<Fine> findByStatus(FineStatus status);

    Optional<Fine> findByLoan_Id(UUID loanId);
}