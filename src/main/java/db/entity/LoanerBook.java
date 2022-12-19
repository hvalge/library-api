package db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "loaner_book")
public class LoanerBook {

    @Id
    private Long id;

    private String title;

    private String author;
}
