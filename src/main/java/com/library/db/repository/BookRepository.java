package com.library.db.repository;

import com.library.db.entity.Book;
import com.library.dto.out.StatusBookDataDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public List<StatusBookDataDTO> getLibraryStatus() {
        return em.createQuery(
                "SELECT new com.library.dto.out.StatusBookDataDTO(" +
                        "b.title, b.copiesAvailable, b.location, b.ageSincePublication) FROM Book b", StatusBookDataDTO.class)
                .getResultList();
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }
}
