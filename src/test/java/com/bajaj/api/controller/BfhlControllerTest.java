package com.bajaj.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testExampleA() throws Exception {
        String requestBody = "{\"data\": [\"a\", \"1\", \"334\", \"4\", \"R\", \"$\"]}";

        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success", is(true)))
                .andExpect(jsonPath("$.user_id", is("yash_upadhyay_21092005")))
                .andExpect(jsonPath("$.email", is("yashupadhyay230834@acropolis.in")))
                .andExpect(jsonPath("$.roll_number", is("0827CI231152")))
                .andExpect(jsonPath("$.odd_numbers", contains("1")))
                .andExpect(jsonPath("$.even_numbers", contains("334", "4")))
                .andExpect(jsonPath("$.alphabets", contains("A", "R")))
                .andExpect(jsonPath("$.special_characters", contains("$")))
                .andExpect(jsonPath("$.sum", is("339")))
                .andExpect(jsonPath("$.concat_string", is("Ra")));
    }

    @Test
    public void testExampleB() throws Exception {
        String requestBody = "{\"data\": [\"2\", \"a\", \"y\", \"4\", \"&\", \"-\", \"*\", \"5\", \"92\", \"b\"]}";

        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success", is(true)))
                .andExpect(jsonPath("$.user_id", is("yash_upadhyay_21092005")))
                .andExpect(jsonPath("$.email", is("yashupadhyay230834@acropolis.in")))
                .andExpect(jsonPath("$.roll_number", is("0827CI231152")))
                .andExpect(jsonPath("$.odd_numbers", contains("5")))
                .andExpect(jsonPath("$.even_numbers", contains("2", "4", "92")))
                .andExpect(jsonPath("$.alphabets", contains("A", "Y", "B")))
                .andExpect(jsonPath("$.special_characters", contains("&", "-", "*")))
                .andExpect(jsonPath("$.sum", is("103")))
                .andExpect(jsonPath("$.concat_string", is("ByA")));
    }

    @Test
    public void testExampleC() throws Exception {
        String requestBody = "{\"data\": [\"A\", \"ABCD\", \"DOE\"]}";

        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success", is(true)))
                .andExpect(jsonPath("$.user_id", is("yash_upadhyay_21092005")))
                .andExpect(jsonPath("$.email", is("yashupadhyay230834@acropolis.in")))
                .andExpect(jsonPath("$.roll_number", is("0827CI231152")))
                .andExpect(jsonPath("$.odd_numbers", hasSize(0)))
                .andExpect(jsonPath("$.even_numbers", hasSize(0)))
                .andExpect(jsonPath("$.alphabets", contains("A", "ABCD", "DOE")))
                .andExpect(jsonPath("$.special_characters", hasSize(0)))
                .andExpect(jsonPath("$.sum", is("0")))
                .andExpect(jsonPath("$.concat_string", is("EoDdCbAa")));
    }

    @Test
    public void testEmptyOrNullRequest() throws Exception {
        mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success", is(false)));
    }
}
