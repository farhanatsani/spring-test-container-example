package com.example.book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private BookService bookService;
    private BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(
                books
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new RuntimeException("Book is not found"));

        return ResponseEntity.ok(
                book
        );
    }
    @PostMapping
    public ResponseEntity<Book> postBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    bookService.save(book)
                );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        Book _book = bookService.findById(id)
                .orElseThrow(() -> new RuntimeException("Book is not found"));
        _book.setTitle(book.getTitle());
        _book.setDescription(book.getDescription());
        _book.setPublishedAt(book.getPublishedAt());

        return ResponseEntity.status(HttpStatus.OK).body(
            bookService.save(_book)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllBook() {
        bookService.deleteAll();

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
