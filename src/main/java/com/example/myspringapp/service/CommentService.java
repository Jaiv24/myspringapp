package com.example.myspringapp.service;

import com.example.myspringapp.model.Comment;
import com.example.myspringapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
//        return "Deletion completed";
    }
}
