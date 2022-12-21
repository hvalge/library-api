package com.library.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min = 1, max = 255)
    private String title;

    @NotNull
    @Length(min = 1, max = 255)
    private String author;

    @Length(min = 1, max = 255)
    private String location;

    @Column(name = "copies_available")
    private int copiesAvailable;

    @NotNull
    @Column(name = "age_since_publication", columnDefinition = "timestamp")
    private LocalDate ageSincePublication;
}
