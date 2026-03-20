package com.Project.LibraryFlow.book.controller;

import com.Project.LibraryFlow.book.dtos.BookRequestDTO;
import com.Project.LibraryFlow.book.dtos.BookResponseDTO;
import com.Project.LibraryFlow.book.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody @Valid BookRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDTO> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
