package com.example.comment;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
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
@DisplayName("Comment Test")
public class CommentControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Order(1)
    @Description("Save Comment on Existing Book")
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

        Allure.step("Save Comment Book 1");
        mockMvc.perform(
                        post("/api/v1/books/1/comments")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(request1st)
                )
                .andExpect(status().isCreated());

    }

}
