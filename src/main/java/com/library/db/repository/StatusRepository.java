package com.library.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StatusRepository {

    @PersistenceContext
    private EntityManager em;

    public String getStatus() {
        var results = em.createQuery("SELECT b.title, b.copiesAvailable, b.location, b.ageSincePublication FROM Book b").getResultList();
        System.out.println(results);
        return "OK";
    }
}
