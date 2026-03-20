package com.Project.LibraryFlow.loan.services;

import com.Project.LibraryFlow.bookCopy.entities.BookCopy;
import com.Project.LibraryFlow.bookCopy.enums.CopyStatus;
import com.Project.LibraryFlow.bookCopy.repository.BookCopyRepository;
import com.Project.LibraryFlow.loan.dtos.LoanRequestDTO;
import com.Project.LibraryFlow.loan.dtos.LoanResponseDTO;
import com.Project.LibraryFlow.loan.entities.Loan;
import com.Project.LibraryFlow.loan.enums.LoanStatus;
import com.Project.LibraryFlow.loan.repositories.LoanRepository;
import com.Project.LibraryFlow.user.entities.User;
import com.Project.LibraryFlow.user.enums.UserType;
import com.Project.LibraryFlow.user.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    public LoanResponseDTO create(LoanRequestDTO dto, Authentication authentication) {

        User loggedUser = (User) authentication.getPrincipal();
        UserType userType = loggedUser.getUserType();

        if (!loggedUser.getId().equals(dto.user())) {
            boolean isAdmin = userType == UserType.ADMIN || userType == UserType.LIBRARIAN;
            if (!isAdmin) { throw new RuntimeException("You can only create loans for yourself"); }
        }
        BookCopy copy = bookCopyRepository.findById(dto.copy()).orElseThrow(() -> new RuntimeException("Book copy not found"));

        if (!copy.isAvailable()) { throw new RuntimeException("Book copy is not available"); }

        Loan loan = new Loan(loggedUser, copy);
        copy.setStatus(CopyStatus.BORROWED);

        loanRepository.save(loan);
        bookCopyRepository.save(copy);

        return LoanResponseDTO.fromEntity(loan);
    }

    public List<LoanResponseDTO> findAll() {
        return loanRepository.findAll().stream()
                .map(LoanResponseDTO::fromEntity)
                .toList();
    }

    public LoanResponseDTO findById(UUID id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return LoanResponseDTO.fromEntity(loan);
    }

    public List<LoanResponseDTO> findByUserId(UUID userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(LoanResponseDTO::fromEntity)
                .toList();
    }

    public LoanResponseDTO returnBook(UUID id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getStatus() == LoanStatus.RETURNED) {
            throw new RuntimeException("Loan already returned");
        }

        loan.setReturnedAt(Instant.now());
        loan.setStatus(LoanStatus.RETURNED);

        BookCopy copy = loan.getCopy();
        copy.setStatus(CopyStatus.AVAILABLE);

        loanRepository.save(loan);
        bookCopyRepository.save(copy);

        return LoanResponseDTO.fromEntity(loan);
    }

    public void delete(UUID id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        BookCopy copy = loan.getCopy();
        copy.setStatus(CopyStatus.AVAILABLE);
        bookCopyRepository.save(copy);

        loanRepository.delete(loan);
    }
}
