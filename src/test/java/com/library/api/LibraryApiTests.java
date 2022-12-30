package com.library.api;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LibraryApiTests {

    @Autowired
    MockMvc mockMvc;

    static final String API_URL = "http://localhost:8080/api";

    @Test
    void invalidLoginShouldReturn401() throws Exception {
        this.mockMvc.perform(post(API_URL + "/login")
                        .contentType(APPLICATION_JSON)
                        .content("{\"username\":\"notarealuser\",\"password\":\"acb\"}"))
                .andReturn().getResponse().getHeader("Authorization");
    }

    @Test
    void validLoginShouldReturn200WithJwt() throws Exception {
        this.mockMvc.perform(post(API_URL + "/login")
                        .contentType(APPLICATION_JSON)
                        .content("{\"username\":\"staff\",\"password\":\"staff\"}"))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getHeader("Authorization").contains("Bearer"));
    }

    @Test
    void searchLoanersUnauthorizedShouldReturn401() throws Exception {
        this.mockMvc.perform(get(API_URL + "/loaners/search"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void postLoanerUnauthorizedShouldReturn401() throws Exception {
        this.mockMvc.perform(post(API_URL + "/loaners")
                        .contentType(APPLICATION_JSON)
                        .content("{\"firstName\":\"Arbitrary\",\"lastName\":\"Name\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getOverdueBooksReportUnauthorizedShouldReturn401() throws Exception {
        this.mockMvc.perform(get(API_URL + "/reports/overdue-books"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void postLoanUnauthorizedShouldReturn401() throws Exception {
        this.mockMvc.perform(post(API_URL + "/loans")
                        .contentType(APPLICATION_JSON)
                        .content("{\"loanerId\":1,\"bookId\":1}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void returnBookUnauthorizedShouldReturn401() throws Exception {
        this.mockMvc.perform(patch(API_URL + "/loans/3/return"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void patchBookLoanDueDateUnauthorizedShouldReturn401() throws Exception {
        this.mockMvc.perform(patch(API_URL + "/loans/3/update-book-loan-due-date")
                        .contentType(APPLICATION_JSON)
                        .content("{\"dueDate\":\"" + LocalDate.now().plusDays(7) + "\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void searchBooksUnauthorizedShouldReturn401() throws Exception {
        this.mockMvc.perform(get(API_URL + "/books/search?title=The&author=J.K."))
                .andExpect(status().isUnauthorized());
    }

}
