package com.library.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private Loaner loaner;

    @NotNull
    @OneToOne
    private Book book;

    @NotNull
    @Column(name = "loaned_at", columnDefinition = "timestamp")
    private LocalDate loanedAt;

    @Column(name = "due_date", columnDefinition = "timestamp")
    private LocalDate dueDate;

    @Column(name = "is_returned")
    private Boolean isReturned;

    @Column(name = "returned_at", columnDefinition = "timestamp")
    private LocalDate returnedAt;
}
