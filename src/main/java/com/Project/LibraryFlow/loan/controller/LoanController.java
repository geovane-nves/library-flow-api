package com.Project.LibraryFlow.loan.controller;

import com.Project.LibraryFlow.loan.dtos.LoanRequestDTO;
import com.Project.LibraryFlow.loan.dtos.LoanResponseDTO;
import com.Project.LibraryFlow.loan.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/loans")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> create(@RequestBody @Valid LoanRequestDTO request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request, authentication));
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanResponseDTO>> findByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PatchMapping("/{id}/return")
    public ResponseEntity<LoanResponseDTO> returnBook(@PathVariable UUID id) {
        return ResponseEntity.ok(service.returnBook(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
