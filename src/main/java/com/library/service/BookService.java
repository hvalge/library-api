package com.library.service;

import com.library.db.entity.Book;
import com.library.db.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchBooks(String title, String author) {
        return bookRepository.findPartialMatchForBooks(title, author);
    }
}
