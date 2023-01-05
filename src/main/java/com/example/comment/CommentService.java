package com.example.comment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<Comment> findByBookId(Long bookId) {
        return commentRepository.findByBookId(bookId);
    }
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
    public Comment save(Comment commentRequest) {
        return commentRepository.save(commentRequest);
    }
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
