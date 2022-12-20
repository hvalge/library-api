package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Loaner loaner;

    @OneToOne
    private Book book;

    @Column(name = "loaned_at", columnDefinition = "timestamp")
    private LocalDate loanedAt;

    @Column(name = "due_date", columnDefinition = "timestamp")
    private LocalDate dueDate;

    @Column(name = "is_returned")
    private boolean isReturned;

    @Column(name = "returned_at", columnDefinition = "timestamp")
    private LocalDate returnedAt;
}
