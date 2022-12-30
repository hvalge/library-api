package com.library.integration;

import com.library.db.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LoanControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LoanRepository loanRepository;

    static final String LOAN_URL = "http://localhost:8080/api/loans";
    static final String LOGIN_ENDPOINT = "http://localhost:8080/api/login";
    String token;

    @BeforeEach
    void login() throws Exception {
        token = this.mockMvc.perform(post(LOGIN_ENDPOINT)
                        .contentType(APPLICATION_JSON)
                        .content("{\"username\":\"staff\",\"password\":\"staff\"}"))
                .andReturn().getResponse().getHeader("Authorization");
    }

    @Test
    void createLoanShouldReturnCreatedLoan() throws Exception {
        this.mockMvc.perform(post(LOAN_URL)
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON)
                        .content("{\"bookId\":1,\"loanerId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void repeatedBookReturnShouldReturnNoContentAndIncreaseCopiesAvailableJustOnce() throws Exception {
        this.loanRepository.findById(3L).ifPresent(loan -> {
            assert loan.getBook().getCopiesAvailable() == 5;
        });

        this.mockMvc.perform(patch(LOAN_URL + "/3/return-book")
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isNoContent());

        this.loanRepository.findById(3L).ifPresent(loan -> {
            assert loan.getBook().getCopiesAvailable() == 6;
        });

        this.mockMvc.perform(patch(LOAN_URL + "/3/return-book")
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON)
                        .content("{\"bookId\":1,\"loanerId\":1}"))
                .andExpect(status().isNoContent());

        this.loanRepository.findById(3L).ifPresent(loan -> {
            assert loan.getBook().getCopiesAvailable() == 6;
        });
    }

    @Test
    void updateBookLoanDueDateOneWeekLaterShouldReturnNoContent() throws Exception {
        this.mockMvc.perform(patch(LOAN_URL + "/3/update-book-loan-due-date")
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON)
                        .content("{\"newDueDate\":\" " + LocalDate.now().plusDays(7) + "\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateBookLoanDueDateOneDayEarlierShouldReturnMethodNotAllowed() throws Exception {
        this.mockMvc.perform(patch(LOAN_URL + "/3/update-book-loan-due-date")
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON)
                        .content("{\"newDueDate\":\" " + LocalDate.now().minusDays(1) + "\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateBookLoanDueDateForReturnedBookShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(patch(LOAN_URL + "/1/update-book-loan-due-date")
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON)
                        .content("{\"newDueDate\":\" " + LocalDate.now().plusDays(7) + "\"}"))
                .andExpect(status().isBadRequest());
    }

}
