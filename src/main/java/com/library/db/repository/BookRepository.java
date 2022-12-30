package com.library.db.repository;

import com.library.db.entity.Book;
import com.library.dto.out.StatusBookDataDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT new com.library.dto.out.StatusBookDataDto(" +
            "b.title, b.copiesAvailable, b.location, b.ageSincePublication) FROM Book b")
    List<StatusBookDataDto> getLibraryStatus();

    @Query("SELECT b FROM Book b " +
            "WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) " +
            "AND LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Book> findPartialMatchForBooksByTitleAndAuthor(String title, String author);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findPartialMatchForBooksByTitle(String title);

    @Query("SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    List<Book> findPartialMatchForBooksByAuthor(String author);

}
