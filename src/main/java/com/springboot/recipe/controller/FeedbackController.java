package com.springboot.recipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.recipe.model.Comment;
import com.springboot.recipe.model.Rating;
import com.springboot.recipe.service.CommentService;
import com.springboot.recipe.service.RatingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/recipes/{id}")
public class FeedbackController {
    @Autowired
    RatingService ratingService;
    @Autowired
    CommentService commentService;

    @PostMapping("/feedback")
    public ResponseEntity<?> rateRecipe(@PathVariable Long id, @RequestBody Rating rating) {
        try {
            return ResponseEntity.status(200).body(ratingService.rateRecipe(id, rating));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error rating recipe: " + e.getMessage());
        }
    }

    @PostMapping("/comments")
    public ResponseEntity<?> addComments(@PathVariable Long id, @RequestBody Comment comment) {
        try {
            return ResponseEntity.status(200).body(commentService.addComment(id, comment));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error commenting recipe: " + e.getMessage());
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getAllComments(@PathVariable Long id) {
        try {
            return ResponseEntity.status(200).body(commentService.getCommentsForRecipe(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting comments: " + e.getMessage());
        }
    }
}
