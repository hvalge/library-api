package com.library.service;

import com.library.db.entity.Book;
import com.library.db.entity.Loan;
import com.library.db.entity.Loaner;
import com.library.db.repository.BookRepository;
import com.library.db.repository.LoanRepository;
import com.library.db.repository.LoanerRepository;
import com.library.dto.in.LoanDTO;
import com.library.dto.in.UpdateBookLoanDueDateDTO;
import com.library.exception.NoCopiesAvailableException;
import com.library.mapper.LoanMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    @Transactional
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan with ID " + loanId + " not found"));

        if (!loan.getIsReturned()) {
            loan.setIsReturned(true);
            loan.getBook().setCopiesAvailable(loan.getBook().getCopiesAvailable() + 1);
            loan.setReturnedAt(LocalDate.now());
        }
    }

    @Transactional
    public void updateBookLoanDueDate(Long loanId, UpdateBookLoanDueDateDTO updateBookLoanDueDateDTO) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new EntityNotFoundException("Loan with ID " + loanId + " not found"));

        if (loan.getIsReturned()) {
            throw new IllegalArgumentException("Book with ID " + loan.getBook().getId() + " has already been returned.");
        }
        if (updateBookLoanDueDateDTO.getNewDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Due date cannot be before today's date.");
        }

        loan.setDueDate(updateBookLoanDueDateDTO.getNewDueDate());
    }

}
