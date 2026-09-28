package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoController.class)
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // -----------------------------------
    // 1. Basic functionality
    // -----------------------------------

    @Test
    void shouldRemoveFirstAndLastCharactersFromEloquent()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "eloquent"))
                .andExpect(status().isOk())
                .andExpect(content().string("loquen"));
    }

    @Test
    void shouldRemoveFirstAndLastCharactersFromCountry()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "country"))
                .andExpect(status().isOk())
                .andExpect(content().string("ountr"));
    }

    @Test
    void shouldRemoveFirstAndLastCharactersFromPerson()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "person"))
                .andExpect(status().isOk())
                .andExpect(content().string("erso"));
    }

    // -----------------------------------
    // 2. Edge cases: 0-3 characters
    // -----------------------------------

    @Test
    void shouldReturnBadRequestForEmptyString()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestForSingleCharacter()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnEmptyStringForTwoCharacters()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "ab"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void shouldReturnMiddleCharacterForThreeCharacters()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "xyz"))
                .andExpect(status().isOk())
                .andExpect(content().string("y"));
    }

    // -----------------------------------
    // 3. Numbers and special characters
    // -----------------------------------

    @Test
    void shouldHandleNumbers()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "12345"))
                .andExpect(status().isOk())
                .andExpect(content().string("234"));
    }

    @Test
    void shouldHandleSpecialCharacters()
            throws Exception {

        mockMvc.perform(get("/remove")
                        .param("original", "*123*%qwerty+"))
                .andExpect(status().isOk())
                .andExpect(content().string("123*%qwerty"));
    }

    @Test
    void shouldReturnBadRequestWhenParameterIsMissing()
            throws Exception {

        mockMvc.perform(get("/remove"))
                .andExpect(status().isBadRequest());
    }
}