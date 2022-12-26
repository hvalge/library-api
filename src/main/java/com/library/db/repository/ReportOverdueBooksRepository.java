package com.library.db.repository;

import com.library.dto.out.OverdueBookDataDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportOverdueBooksRepository {

    @PersistenceContext
    private EntityManager em;

    public List<OverdueBookDataDTO> getOverdueBooks() {
        return em.createQuery(
                "SELECT new com.library.dto.out.OverdueBookDataDTO(" +
                        "CONCAT(loaner.firstName, ' ', loaner.lastName), book.title, l.dueDate, l.isReturned, l.returnedAt) " +
                        "FROM Loan l " +
                        "JOIN l.book book " +
                        "JOIN l.loaner loaner ", OverdueBookDataDTO.class)
                .getResultList();
    }


}
