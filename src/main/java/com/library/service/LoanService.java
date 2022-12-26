package com.library.service;

import com.library.db.entity.Book;
import com.library.db.entity.Loan;
import com.library.db.entity.Loaner;
import com.library.db.repository.BookRepository;
import com.library.db.repository.LoanRepository;
import com.library.db.repository.LoanerRepository;
import com.library.dto.in.LoanDTO;
import com.library.exception.NoCopiesAvailableException;
import com.library.mapper.LoanMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanerRepository loanerRepository;
    private final BookRepository bookRepository;
    private final LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository, LoanerRepository loanerRepository,
                       BookRepository bookRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanerRepository = loanerRepository;
        this.bookRepository = bookRepository;
        this.loanMapper = loanMapper;
    }

    @Transactional
    public Loan createLoan(LoanDTO loanDTO) {
        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Loaner with ID " + loanDTO.getLoanerId() + " not found"));

        if (book.getCopiesAvailable() == 0) {
            throw new NoCopiesAvailableException("No copies available of book " + book.getTitle() + " to loan out.");
        }
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);

        Loaner loaner = loanerRepository.findById(loanDTO.getLoanerId())
                .orElseThrow(() -> new EntityNotFoundException("Loaner with ID " + loanDTO.getLoanerId() + " not found"));


        Loan loan = loanMapper.mapBookAndLoanerToLoan(loaner, book);

        return loanRepository.save(loan);
    }

}
