package com.example.myspringapp.resourse;

import com.example.myspringapp.model.Comment;
import com.example.myspringapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentResourse {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public Comment saveComment(@RequestBody Comment comment){return commentService.saveComment(comment);}

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PutMapping
    public Comment updateComment(@RequestBody Comment comment){
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/comment")
    public void deleteComment(@RequestParam(name = "commentId") String commentId){
        commentService.deleteComment(commentId);
    }
}
