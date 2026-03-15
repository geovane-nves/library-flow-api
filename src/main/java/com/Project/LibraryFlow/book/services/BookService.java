package com.Project.LibraryFlow.book.services;

import com.Project.LibraryFlow.author.entities.Author;
import com.Project.LibraryFlow.author.repositories.AuthorRepository;
import com.Project.LibraryFlow.book.dtos.BookRequestDTO;
import com.Project.LibraryFlow.book.dtos.BookResponseDTO;
import com.Project.LibraryFlow.book.entities.Book;
import com.Project.LibraryFlow.book.repositories.BookRepository;
import com.Project.LibraryFlow.category.entities.Category;
import com.Project.LibraryFlow.category.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;

    public BookResponseDTO create(BookRequestDTO dto) {
        Author author = authorRepository.findById(dto.authorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Category category = categoryRepository.findById(dto.categoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = new Book(
                author,
                category,
                dto.title(),
                dto.isbn(),
                dto.publicationYear(),
                dto.description()
        );
        return BookResponseDTO.fromEntity(bookRepository.save(book));
    }

    public List<BookResponseDTO> findAll(){
        return bookRepository.findAll().stream()
                .map(BookResponseDTO::fromEntity)
                .toList();
    }

    public BookResponseDTO findById(UUID id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return BookResponseDTO.fromEntity(book);
    }

    public void delete(UUID id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        bookRepository.delete(book);
    }
}
