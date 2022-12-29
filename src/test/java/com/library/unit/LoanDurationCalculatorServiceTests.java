package com.library.unit;

import com.library.service.LoanDurationCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class LoanDurationCalculatorServiceTests {

    @Autowired
    private LoanDurationCalculatorService loanDurationCalculatorService;

    LocalDate dayLoaned;

    @BeforeEach
    public void setUp() {
        dayLoaned = LocalDate.now();
    }

    @Test
    void testCalculateLoanDueDateShouldBePlusFourWeeksWhenFiveCopiesAvailableAndBookIsNotNew() {
        var publishingDate = dayLoaned.minusMonths(3);
        int copiesAvailable = 5;

        var loanDuration = loanDurationCalculatorService.calculateLoanDueDate(dayLoaned, publishingDate, copiesAvailable);

        var expected = LocalDate.now().plusWeeks(4);
        assertEquals("Expected loan deadline to be plus four weeks", expected, loanDuration);
    }

    @Test
    void testCalculateLoanDueDateShouldBePlusOneWeekWhenLessThanFiveCopiesAvailable() {
        var publishingDate = dayLoaned.minusMonths(1);
        int copiesAvailable = 4;

        var dueDate = loanDurationCalculatorService.calculateLoanDueDate(dayLoaned, publishingDate, copiesAvailable);

        var expected = LocalDate.now().plusWeeks(1);
        assertEquals("Expected loan deadline to be plus one week", expected, dueDate);
    }

    @Test
    void testCalculateLoanDueDateShouldBePlusOneWeekWhenBookIsNew() {
        var publishingDate = dayLoaned.minusMonths(3).plusDays(1);
        int copiesAvailable = 5;

        var dueDate = loanDurationCalculatorService.calculateLoanDueDate(dayLoaned, publishingDate, copiesAvailable);

        var expected = LocalDate.now().plusWeeks(1);
        assertEquals("Expected loan deadline to be plus one week", expected, dueDate);
    }

    @Test
    void testCalculateLoanDueDateShouldBePlusOneWeekWhenBookIsNewAndLessThanFiveCopiesAvailable() {
        var publishingDate = dayLoaned.minusMonths(2).plusDays(1);
        int copiesAvailable = 4;

        var loanDuration = loanDurationCalculatorService.calculateLoanDueDate(dayLoaned, publishingDate, copiesAvailable);

        var expected = LocalDate.now().plusWeeks(1);
        assertEquals("Expected loan deadline to be plus one week", expected, loanDuration);
    }
}
