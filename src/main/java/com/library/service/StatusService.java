package com.library.service;

import com.library.db.repository.BookRepository;
import com.library.dto.out.StatusBookDTO;
import com.library.dto.out.StatusBookDataDTO;
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

    public List<StatusBookDTO> getStatus() {
        List<StatusBookDataDTO> books = bookRepository.getLibraryStatus();

        List<StatusBookDTO> result = new ArrayList<>();

        for (StatusBookDataDTO book : books) {
            result.add(bookMapper.statusBookDataToStatusBook(book));
        }

        return result;
    }
}
