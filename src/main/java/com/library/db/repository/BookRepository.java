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

    public List<Book> findPartialMatchForBooks(String title, String author) {
        String sql;
        if (title != null && author != null) {
            sql = "SELECT b FROM Book b WHERE b.title LIKE :title AND b.author LIKE :author";
        } else if (title != null) {
            sql = "SELECT b FROM Book b WHERE b.title LIKE :title";
        } else {
            sql = "SELECT b FROM Book b WHERE b.author LIKE :author";
        }

        var query = em.createQuery(sql, Book.class);

        if (title != null) {
            query.setParameter("title", "%" + title + "%");
        }
        if (author != null) {
            query.setParameter("author", "%" + author + "%");
        }

        return query.getResultList();
    }
}
