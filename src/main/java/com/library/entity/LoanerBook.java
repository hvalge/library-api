package com.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "loaner_book")
public class LoanerBook {

    @Id
    private Long id;

    @OneToOne
    private Loaner loaner;

    @OneToOne
    private Book book;
}
