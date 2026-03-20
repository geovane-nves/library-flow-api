package com.Project.LibraryFlow.user.controller;

import com.Project.LibraryFlow.user.dtos.UserUpdateDTO;
import com.Project.LibraryFlow.user.entities.User;
import com.Project.LibraryFlow.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PutMapping("/me")
    public User update(@RequestBody UserUpdateDTO dto, Authentication authentication) {
        User loggedUser = (User) authentication.getPrincipal();
        return service.update(loggedUser.getId(), dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}