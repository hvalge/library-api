package com.library.db.repository;

import com.library.db.entity.Loaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanerRepository extends JpaRepository<Loaner, Long> {}
