package com.library.controller;

import com.library.db.entity.Book;
import com.library.service.BookService;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) @Length(min = 1, max = 255) String title,
                                  @RequestParam(required = false) @Length(min = 1, max = 255) String author) {
        if (title == null && author == null) {
            throw new IllegalArgumentException("At least title or author parameters must be provided");
        }
        return bookService.searchBooks(title, author);
    }
}
