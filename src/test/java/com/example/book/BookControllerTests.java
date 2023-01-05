package com.example.book;

import org.junit.jupiter.api.*;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:tc:postgresql:latest:///")
@AutoConfigureMockMvc
public class BookControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Order(1)
    void saveBook() throws Exception {

        /*
            Save 1 book using POST with Request body :

            {
                "title": "Java Book",
                "description": "Deskription of Java Book",
                "publishedAt": "2020-01-01"
            }

        */

        String request = "{ \"title\": \"Java Book\", \"description\": \"Deskription of Java Book\", \"publishedAt\": \"2020-01-01\" }";
        mockMvc.perform(
                        post("/api/v1/books")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(request)
                )
                .andExpect(status().isCreated());
    }
    @Test
    @Order(2)
    void findBookById() throws Exception {

        /*
            Find book with id 1, using GET and should have Response body :

            {
                "id": 1,
                "title": "Java Book",
                "description": "Deskription of Java Book",
                "publishedAt": "2020-01-01"
            }

        */
        String expectedResponse = " { \"id\": 1, \"title\": \"Java Book\", \"description\": \"Deskription of Java Book\", \"publishedAt\": \"2020-01-01\" }";

        String result = mockMvc.perform(get("/api/v1/books/1"))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        JSONAssert.assertEquals(expectedResponse, result, false);
    }
}
