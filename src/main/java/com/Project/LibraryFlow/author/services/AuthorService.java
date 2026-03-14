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

    public AuthorResponseDTO create(AuthorRequestDTO request){
        Author author = new Author();
        author.setName(request.name());
        author.setBiography(request.biography());

        repository.save(author);
        return new AuthorResponseDTO(author);
    }

    public List<AuthorResponseDTO> findAll(){
        List<Author> list = repository.findAll();
        return list.stream()
                .map(AuthorResponseDTO::new)
                .toList();
    }

    public AuthorResponseDTO findById(UUID id){
        return repository.findById(id)
                .map(AuthorResponseDTO::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }

    public AuthorResponseDTO delete(UUID id){
        Author author = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        repository.deleteById(id);
        return new AuthorResponseDTO(author);
    }
}
