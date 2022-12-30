package com.library.integration;

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
public class BookControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    static final String BOOK_URL = "http://localhost:8080/api/books";
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
    void searchBooksWithoutTitleOrAuthorShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(get(BOOK_URL + "/search")
                        .header("Authorization", token))
                .andExpect(status().isBadRequest());
    }

    @Test
    void searchBooksWithTitleAndNoAuthorGetsCorrectResults() throws Exception {
        this.mockMvc.perform(get(BOOK_URL + "/search")
                        .header("Authorization", token)
                        .param("title", "The"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void searchBooksWithAuthorAndNoTitleGetsCorrectResults() throws Exception {
        this.mockMvc.perform(get(BOOK_URL + "/search")
                        .header("Authorization", token)
                        .param("author", "J"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void searchBooksWithTitleAndAuthorGetsCorrectResults() throws Exception {
        this.mockMvc.perform(get(BOOK_URL + "/search")
                        .header("Authorization", token)
                        .param("title", "The")
                        .param("author", "J"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
