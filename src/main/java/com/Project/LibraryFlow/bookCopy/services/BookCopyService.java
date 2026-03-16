package com.Project.LibraryFlow.bookCopy.services;

import com.Project.LibraryFlow.book.dtos.BookRequestDTO;
import com.Project.LibraryFlow.book.dtos.BookResponseDTO;
import com.Project.LibraryFlow.book.entities.Book;
import com.Project.LibraryFlow.book.repositories.BookRepository;
import com.Project.LibraryFlow.bookCopy.dtos.BookCopyRequestDTO;
import com.Project.LibraryFlow.bookCopy.dtos.BookCopyResponseDTO;
import com.Project.LibraryFlow.bookCopy.dtos.CopyStatsDTO;
import com.Project.LibraryFlow.bookCopy.dtos.UpdateCopyRequestDTO;
import com.Project.LibraryFlow.bookCopy.entities.BookCopy;
import com.Project.LibraryFlow.bookCopy.repository.BookCopyRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookCopyResponseDTO create(BookCopyRequestDTO dto){
        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        BookCopy copy = new BookCopy(
                book,
                dto.shelfLocation()
        );
        return BookCopyResponseDTO.fromEntity(bookCopyRepository.save(copy));
    }

    public List<BookCopyResponseDTO> findAll(){
        return bookCopyRepository.findAll().stream()
                .map(BookCopyResponseDTO::fromEntity)
                .toList();
    }

    public BookCopyResponseDTO findById(UUID id){
        BookCopy copy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Copy not found"));
        return BookCopyResponseDTO.fromEntity(copy);
    }

    public CopyStatsDTO stats(UUID id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return CopyStatsDTO.fromRepository(bookCopyRepository, id);
    }

    public BookCopyResponseDTO update(UUID id, UpdateCopyRequestDTO dto){
        BookCopy copy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Copy not found"));
        dto.applyTo(copy);
        bookCopyRepository.save(copy);
        return BookCopyResponseDTO.fromEntity(copy);
    }

    public void delete(UUID id){
        BookCopy copy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Copy not found"));
        bookCopyRepository.delete(copy);
    }

}
