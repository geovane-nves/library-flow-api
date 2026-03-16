package com.Project.LibraryFlow.bookCopy.controller;

import com.Project.LibraryFlow.bookCopy.dtos.BookCopyRequestDTO;
import com.Project.LibraryFlow.bookCopy.dtos.BookCopyResponseDTO;
import com.Project.LibraryFlow.bookCopy.dtos.CopyStatsDTO;
import com.Project.LibraryFlow.bookCopy.dtos.UpdateCopyRequestDTO;
import com.Project.LibraryFlow.bookCopy.services.BookCopyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/copies")
public class BookCopyController {

    @Autowired
    private BookCopyService service;

    @PostMapping
    public ResponseEntity<BookCopyResponseDTO> create(@Valid @RequestBody BookCopyRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<BookCopyResponseDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCopyResponseDTO> findById(@Valid @PathVariable UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<CopyStatsDTO> copyStats(@PathVariable UUID id) {
        return ResponseEntity.ok(service.stats(id));
    }

    @PutMapping("/{id}")
    public BookCopyResponseDTO update(@PathVariable UUID id, @RequestBody UpdateCopyRequestDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookCopyResponseDTO> delete(@Valid @PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
