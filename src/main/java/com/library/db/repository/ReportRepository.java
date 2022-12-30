package com.library.db.repository;

import com.library.db.entity.Loan;
import com.library.dto.out.OverdueBookDataDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT new com.library.dto.out.OverdueBookDataDto(" +
            "CONCAT(loaner.firstName, ' ', loaner.lastName), book.title, l.dueDate, l.isReturned, l.returnedAt) " +
            "FROM Loan l " +
            "JOIN l.book book " +
            "JOIN l.loaner loaner")
    List<OverdueBookDataDto> getOverdueBooks();

}
