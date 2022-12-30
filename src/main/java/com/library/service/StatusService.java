package com.library.service;

import com.library.db.repository.BookRepository;
import com.library.dto.out.StatusBookDto;
import com.library.dto.out.StatusBookDataDto;
import com.library.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;


    public StatusService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<StatusBookDto> getStatus() {
        List<StatusBookDataDto> books = bookRepository.getLibraryStatus();

        List<StatusBookDto> result = new ArrayList<>();

        for (StatusBookDataDto book : books) {
            result.add(bookMapper.statusBookDataToStatusBook(book));
        }

        return result;
    }
}
