package com.library.db.repository;

import com.library.db.entity.Loaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanerRepository extends JpaRepository<Loaner, Long> {

    @Query("SELECT l FROM Loaner l " +
            "WHERE LOWER(l.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(l.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Loaner> findPartialMatchForLoaners(String name);

}
