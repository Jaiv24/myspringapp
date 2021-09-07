package com.example.myspringapp.resourse;

import com.example.myspringapp.exception.InvalidTokenException;
import com.example.myspringapp.exception.UserNotAuthorizedException;
import com.example.myspringapp.model.Comment;
import com.example.myspringapp.model.FirebaseUser;
import com.example.myspringapp.service.CommentService;
import com.example.myspringapp.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentResourse {
    @Autowired
    private CommentService commentService;


    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/photos/comments")
    public Comment saveComment(@RequestBody @Valid Comment comment,
                               @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            comment.setCreatedBy(firebaseUser.getEmail());
            if (comment.getDateCreated() == null) {
                comment.setDateCreated(java.time.LocalDate.now());
            }
            return commentService.saveComment(comment);
        } else {
            throw new InvalidTokenException();
        }
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PutMapping("/photos/comment/{commentId}")
    public Comment updateComment(@RequestBody @Valid Comment comment,
                                 @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(comment.getCreatedBy())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "Comment.createdBy: " + comment.getCreatedBy()
                );
            } else {
                return commentService.updateComment(comment);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    @DeleteMapping("/photos/comment/{commentId}")
    public String deleteComment(@RequestParam(name = "commentId") String commentId,
                                @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        Comment comment = commentService.getCommentById(commentId);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(comment.getCreatedBy())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "Comment.createdBy: " + comment.getCreatedBy()
                );
            } else {
                return commentService.deleteComment(commentId);
            }
        } else {
            throw new InvalidTokenException();
        }
    }
}
