package com.example.comment;

import com.example.book.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private BookService bookService;
    private CommentService commentService;
    public CommentController(BookService bookService, CommentService commentService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }
    @GetMapping("/books/{bookId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByBookId(@PathVariable(value = "bookId") Long bookId) {
        bookService.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book is not found"));

        List<Comment> comments = commentService.findByBookId(bookId);
        return ResponseEntity.ok(
                comments
        );
    }
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "id") Long id) {
        Comment comment = commentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment is not found"));

        return ResponseEntity.ok(
            comment
        );
    }
    @PostMapping("/books/{bookId}/comments")
    public ResponseEntity<Comment> createComment(
            @PathVariable(value = "bookId") Long bookId,
            @RequestBody Comment commentRequest) {

        Comment comment = bookService.findById(bookId)
                .map(book -> {
                    commentRequest.setBook(book);
                    return commentService.save(commentRequest);
                }).orElseThrow(() -> new RuntimeException("Comment is not found for book id = " + bookId));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(comment);
    }
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> putComment(
            @PathVariable("id") Long id,
            @RequestBody Comment commentRequest) {

        Comment comment = commentService.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment is not found for book id = " + id));

        comment.setComment(comment.getComment());

        return ResponseEntity.status(HttpStatus.OK).body(
            commentService.save(comment)
        );
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/books/{bookId}/comments")
    public ResponseEntity<HttpStatus> deleteAllCommentOfBook(@PathVariable("bookId") Long bookId) {
        bookService.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book is not found id = " + bookId));

        bookService.deleteByBookId(bookId);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
