package com.library.controller;

import com.library.db.entity.Loan;
import com.library.dto.in.LoanDto;
import com.library.dto.in.UpdateBookLoanDueDateDto;
import com.library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Loan createLoan(@RequestBody @Valid LoanDto loanDTO) {
        return loanService.createLoan(loanDTO);
    }

    @PatchMapping("/{id}/return-book")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(@PathVariable long id) {
        loanService.returnBook(id);
    }

    @PatchMapping("/{id}/update-book-loan-due-date")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBookLoanDueDate(@PathVariable long id, @RequestBody @Valid UpdateBookLoanDueDateDto updateBookLoanDueDateDTO) {
        loanService.updateBookLoanDueDate(id, updateBookLoanDueDateDTO);
    }
}
