package com.Project.LibraryFlow.bookCopy.repository;

import com.Project.LibraryFlow.bookCopy.entities.BookCopy;
import com.Project.LibraryFlow.bookCopy.enums.CopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, UUID> {

    long countByBookId(UUID bookId);

    long countByBookIdAndStatus(UUID bookId, CopyStatus status);

    boolean existsByBookIdAndStatus(UUID bookId, CopyStatus status);

}
