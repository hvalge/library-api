package com.library.mapper;

import com.library.db.entity.Book;
import com.library.db.entity.Loan;
import com.library.db.entity.Loaner;
import com.library.service.LoanDurationCalculatorService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoanMapper {

    private final LoanDurationCalculatorService loanDurationCalculatorService;

    public LoanMapper(LoanDurationCalculatorService loanDurationCalculatorService) {
        this.loanDurationCalculatorService = loanDurationCalculatorService;
    }

    public Loan mapBookAndLoanerToLoan(Loaner loaner, Book book) {
        Loan loan = new Loan();
        loan.setLoaner(loaner);
        loan.setBook(book);
        loan.setLoanedAt(LocalDate.now());
        loan.setReturned(false);
        loan.setDueDate(loanDurationCalculatorService.calculateLoanDueDate(
                LocalDate.now(), book.getAgeSincePublication(), book.getCopiesAvailable()));
        return loan;
    }
}
