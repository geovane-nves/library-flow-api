package com.Project.LibraryFlow.category.services;

import com.Project.LibraryFlow.category.dtos.CategoryRequestDTO;
import com.Project.LibraryFlow.category.dtos.CategoryResponseDTO;
import com.Project.LibraryFlow.category.entities.Category;
import com.Project.LibraryFlow.category.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryResponseDTO create(CategoryRequestDTO dto){
        Category category = new Category(dto.name());
        return CategoryResponseDTO.fromEntity(repository.save(category));
    }

    public List<CategoryResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(CategoryResponseDTO::fromEntity)
                .toList();
    }

    public CategoryResponseDTO findById(UUID id){
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return CategoryResponseDTO.fromEntity(category);
    }

    public void delete(UUID id){
        Category category = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        repository.delete(category);
    }
}

