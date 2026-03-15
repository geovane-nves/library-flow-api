package com.Project.LibraryFlow.author.services;

import com.Project.LibraryFlow.author.dtos.AuthorRequestDTO;
import com.Project.LibraryFlow.author.dtos.AuthorResponseDTO;
import com.Project.LibraryFlow.author.entities.Author;
import com.Project.LibraryFlow.author.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public AuthorResponseDTO create(AuthorRequestDTO dto){
        Author author = new Author(
                dto.name(),
                dto.biography()
        );
        return AuthorResponseDTO.fromEntity(repository.save(author));
    }

    public List<AuthorResponseDTO> findAll(){
        return repository.findAll().stream()
                .map(AuthorResponseDTO::fromEntity)
                .toList();
    }

    public AuthorResponseDTO findById(UUID id){
        Author author = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
        return AuthorResponseDTO.fromEntity(author);
    }

    public void delete(UUID id){
        Author author = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
        repository.delete(author);
    }
}
