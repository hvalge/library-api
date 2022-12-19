package db.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "loaner")
public class Loaner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;
}
