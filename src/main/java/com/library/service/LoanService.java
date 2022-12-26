package com.library.service;

import com.library.db.entity.Book;
import com.library.db.entity.Loan;
import com.library.db.entity.Loaner;
import com.library.db.repository.BookRepository;
import com.library.db.repository.LoanRepository;
import com.library.db.repository.LoanerRepository;
import com.library.dto.in.LoanDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final LoanerRepository loanerRepository;
    private final BookRepository bookRepository;
    private final LoanDurationCalculatorService loanDurationCalculatorService;

    public LoanService(LoanRepository loanRepository, LoanerRepository loanerRepository, BookRepository bookRepository,
                       LoanDurationCalculatorService loanDurationCalculatorService) {
        this.loanRepository = loanRepository;
        this.loanerRepository = loanerRepository;
        this.bookRepository = bookRepository;
        this.loanDurationCalculatorService = loanDurationCalculatorService;
    }

    @Transactional
    public Loan createLoan(LoanDTO loanDTO) {
        Loaner loaner = loanerRepository.findById(loanDTO.getLoanerId())
                .orElseThrow(() -> new EntityNotFoundException("Loaner with ID " + loanDTO.getLoanerId() + " not found"));

        Book book = bookRepository.findById(loanDTO.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Loaner with ID " + loanDTO.getLoanerId() + " not found"));

        Loan loan = new Loan();


        loan.setLoaner(loaner);
        loan.setBook(book);
        loan.setLoanedAt(LocalDate.now());
        loan.setReturned(false);
        loan.setDueDate(loanDurationCalculatorService.calculateLoanDueDate(
                LocalDate.now(), book.getAgeSincePublication(), book.getCopiesAvailable()));

        return loanRepository.save(loan);
    }

}
