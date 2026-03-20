package com.Project.LibraryFlow.loan.entities;

import com.Project.LibraryFlow.bookCopy.entities.BookCopy;
import com.Project.LibraryFlow.loan.enums.LoanStatus;
import com.Project.LibraryFlow.user.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class Loan implements Serializable {

    @Id
    @UuidGenerator
    @Column(name = "loan_id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private BookCopy copy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant loanDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant dueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant returnedAt;

    private LoanStatus status;

    public Loan() {
    }

    public Loan(User user, BookCopy copy) {
        this.user = user;
        this.copy = copy;
        this.loanDate = Instant.now();
        this.dueDate = loanDate.plus(30, ChronoUnit.DAYS);
        this.status = LoanStatus.ACTIVE;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookCopy getCopy() {
        return copy;
    }

    public void setCopy(BookCopy copy) {
        this.copy = copy;
    }

    public Instant getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Instant loanDate) {
        this.loanDate = loanDate;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public Instant getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Instant returnedAt) {
        this.returnedAt = returnedAt;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
