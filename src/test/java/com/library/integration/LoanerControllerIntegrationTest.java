package com.library.integration;

import com.library.db.repository.LoanerRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class LoanerControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LoanerRepository loanRepository;

    static final String LOANER_URL = "http://localhost:8080/api/loaners";
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
    void createLoanerShouldReturnCreatedLoaner() throws Exception {
        this.mockMvc.perform(post(LOANER_URL)
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON)
                        .content("{\"firstName\":\"Arbitrary\",\"lastName\":\"Name\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap());
    }

    @Test
    void searchLoanerWithNameShouldReturnLoaners() throws Exception {
        System.out.println(LOANER_URL + "/search?name=John");
        this.mockMvc.perform(get(LOANER_URL + "/search?name=John")
                        .header("Authorization", token)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
