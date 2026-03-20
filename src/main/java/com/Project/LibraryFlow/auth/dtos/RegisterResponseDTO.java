package com.Project.LibraryFlow.auth.dtos;

import com.Project.LibraryFlow.user.entities.User;
import com.Project.LibraryFlow.user.enums.UserType;

import java.time.Instant;
import java.util.UUID;

public record RegisterResponseDTO(
        UUID id,
        String name,
        String email,
        UserType userType,
        Instant createdAt
) {
    public static RegisterResponseDTO fromEntity(User user){
        return new RegisterResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserType(),
                user.getCreatedAt()
        );
    }
}
