package com.Project.LibraryFlow.category.dtos;

import com.Project.LibraryFlow.category.entities.Category;

import java.util.UUID;

public record CategoryResponseDTO(
        UUID id,
        String name
) {
    public static CategoryResponseDTO fromEntity(Category category){
       return new CategoryResponseDTO(
               category.getId(),
               category.getName()
       );
    }
}
