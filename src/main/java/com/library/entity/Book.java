package com.library.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @Column(name = "copies_available")
    private int copiesAvailable;

    @Column(name = "age_since_publication", columnDefinition = "timestamp")
    private LocalDate ageSincePublication;
}
