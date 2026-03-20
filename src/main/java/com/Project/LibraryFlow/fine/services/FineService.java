package com.Project.LibraryFlow.fine.services;

import com.Project.LibraryFlow.fine.dtos.FineResponseDTO;
import com.Project.LibraryFlow.fine.entities.Fine;
import com.Project.LibraryFlow.fine.enums.FineStatus;
import com.Project.LibraryFlow.fine.repositories.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FineService {

    @Autowired
    private FineRepository repository;

    public List<FineResponseDTO> findPendingFines() {
        return repository.findByStatus(FineStatus.PENDING)
                .stream()
                .map(FineResponseDTO::fromEntity)
                .toList();
    }

    public void payFine(UUID id) {
        Fine fine = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fine not found"));

        fine.setStatus(FineStatus.PAID);
        repository.save(fine);
    }
}