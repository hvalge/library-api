package com.library.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanDurationCalculatorService {

    public LocalDate calculateLoanDueDate(LocalDate dayLoaned, LocalDate publishingDate, int copiesAvailable) {
        return dayLoaned.plusDays(calculateLoanDurationInDays(dayLoaned, publishingDate, copiesAvailable));
    }

    private int calculateLoanDurationInDays(LocalDate dayLoaned, LocalDate publishingDate, int copiesAvailable) {
        if (publishingDate.isAfter(dayLoaned.minusMonths(3)) || copiesAvailable < 5) {
            return 7;
        } else {
            return 28;
        }
    }
}
