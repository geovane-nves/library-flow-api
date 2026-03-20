package com.Project.LibraryFlow.fine.entities;

import com.Project.LibraryFlow.fine.enums.FineStatus;
import com.Project.LibraryFlow.loan.entities.Loan;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
public class Fine {

    @Id
    @GeneratedValue
    @Column(name = "fine_id", nullable = false, unique = true)
    private UUID id;

    private BigDecimal amount;

    private Integer daysLate;

    @Enumerated(EnumType.STRING)
    private FineStatus status;

    private Instant createdAt;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public Fine() {}

    public UUID getId() { return id; }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Integer getDaysLate() { return daysLate; }

    public void setDaysLate(Integer daysLate) { this.daysLate = daysLate; }

    public FineStatus getStatus() { return status; }

    public void setStatus(FineStatus status) { this.status = status; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Loan getLoan() { return loan; }

    public void setLoan(Loan loan) { this.loan = loan; }
}