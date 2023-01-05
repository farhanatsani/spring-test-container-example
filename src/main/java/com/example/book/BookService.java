package com.example.book;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
    public void deleteAll() {
        bookRepository.deleteAll();
    }
    public void deleteByBookId(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
