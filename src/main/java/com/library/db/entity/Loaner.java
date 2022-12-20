package com.library.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "loaners")
public class Loaner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 1, max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Length(min = 1, max = 255)
    @Column(name = "last_name")
    private String lastName;
}
