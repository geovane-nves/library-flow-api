package com.Project.LibraryFlow.reservation.controller;

import com.Project.LibraryFlow.reservation.dtos.ReservationRequestDTO;
import com.Project.LibraryFlow.reservation.dtos.ReservationResponseDTO;
import com.Project.LibraryFlow.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> reserve(@RequestBody ReservationRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.reserveBook(request.userId(), request.bookId()));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<ReservationResponseDTO>> getQueue(@PathVariable UUID bookId) {
        return ResponseEntity.ok(service.getQueue(bookId));
    }
}