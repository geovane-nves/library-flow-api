package com.Project.LibraryFlow.fine.controller;

import com.Project.LibraryFlow.fine.dtos.FineResponseDTO;
import com.Project.LibraryFlow.fine.entities.Fine;
import com.Project.LibraryFlow.fine.services.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fines")
public class FineController {

    @Autowired
    private FineService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @GetMapping("/pending")
    public ResponseEntity<List<FineResponseDTO>> getPendingFines() {
        return ResponseEntity.ok(service.findPendingFines());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PatchMapping("/{id}/pay")
    public ResponseEntity<Void> payFine(@PathVariable UUID id) {
        service.payFine(id);
        return ResponseEntity.noContent().build();
    }
}