package com.example.book;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.datasource.url=jdbc:tc:postgresql:latest:///")
public class BookControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Order(1)
    public void saveBook() throws Exception {

        /*
         *
            Save 1st book using POST with Request body :

            {
                "title": "Java Book",
                "description": "Description of Java Book",
                "publishedAt": "2020-01-01"
            }
        *
        */

        String request1st = "{ \"title\": \"Java Book\", \"description\": \"Description of Java Book\", \"publishedAt\": \"2020-01-01\" }";
        mockMvc.perform(
                        post("/api/v1/books")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(request1st)
                )
                .andExpect(status().isCreated());


        /*
         *
            Save 2nd book using POST with Request body :

            {
                "title": "PostgreSQL Book",
                "description": "Description of PostgreSQL Book",
                "publishedAt": "2020-01-02"
            }
        *
        */

        String request2nd = "{ \"title\": \"PostgreSQL Book\", \"description\": \"Description of PostgreSQL Book\", \"publishedAt\": \"2020-01-02\" }";
        mockMvc.perform(
                        post("/api/v1/books")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(request2nd)
                )
                .andExpect(status().isCreated());


        /*
         *
            Save 3rd book using POST with Request body :

            {
                "title": "Kubernetes Book",
                "description": "Description of Kubernetes Book",
                "publishedAt": "2020-01-03"
            }
        *
        */

        String request3rd = "{ \"title\": \"Kubernetes Book\", \"description\": \"Description of Kubernetes Book\", \"publishedAt\": \"2020-01-03\" }";
        mockMvc.perform(
                        post("/api/v1/books")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(request3rd)
                )
                .andExpect(status().isCreated());
    }
    @Test
    @Order(2)
    public void findBookById() throws Exception {

        /*
         *
            Find book with id 1, using GET and should have Response body :

            {
                "id": 1,
                "title": "Java Book",
                "description": "Description of Java Book",
                "publishedAt": "2020-01-01"
            }
        *
        */
        String expectedResponse1st = " { \"id\": 1, \"title\": \"Java Book\", \"description\": \"Description of Java Book\", \"publishedAt\": \"2020-01-01\" }";

        String result = mockMvc.perform(get("/api/v1/books/1"))
                            .andExpect(status().isOk())
                            .andReturn()
                            .getResponse()
                            .getContentAsString();

        JSONAssert.assertEquals(expectedResponse1st, result, false);


         /*
          *
            Find book with id 2, using GET and should have Response body :

            {
                "id": 2,
                "title": "PostgreSQL Book",
                "description": "Description of PostgreSQL Book",
                "publishedAt": "2020-01-02"
            }
        *
        */
        String expectedResponse2nd = " { \"id\": 2, \"title\": \"PostgreSQL Book\", \"description\": \"Description of PostgreSQL Book\", \"publishedAt\": \"2020-01-02\" }";

        String result2nd = mockMvc.perform(get("/api/v1/books/2"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedResponse2nd, result2nd, false);


        /*
         *
            Find book with id 3, using GET and should have Response body :

            {
                "id": 3,
                "title": "Kubernetes Book",
                "description": "Description of Kubernetes Book",
                "publishedAt": "2020-01-03"
            }
        *
        */
        String expectedResponse3rd = " { \"id\": 3, \"title\": \"Kubernetes Book\", \"description\": \"Description of Kubernetes Book\", \"publishedAt\": \"2020-01-03\" }";

        String result3rd = mockMvc.perform(get("/api/v1/books/3"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedResponse3rd, result3rd, false);
    }

    @Test
    @Order(3)
    public void findBookByNonExistingId() throws Exception {

        mockMvc.perform(get("/api/v1/books/5"))
                .andExpect(status().isNotFound());

    }
}
