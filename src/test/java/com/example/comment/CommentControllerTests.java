package com.example.comment;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:tc:postgresql:latest:///")
public class CommentControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Order(1)
    void saveComment() throws Exception {

        /*
         *
            Save comment for book id 1, using POST with Request body :

            {
                "comment": "Bukunya bagus",
                "dateTime": "2021-02-01T10:00:00"
            }
        *
        */

        String request1st = "{ \"comment\": \"Bukunya bagus\", \"dateTime\": \"2021-02-01T10:00:00\" }";
        mockMvc.perform(
                        post("/api/v1/books/1/comments")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(request1st)
                )
                .andExpect(status().isCreated());

    }

}
