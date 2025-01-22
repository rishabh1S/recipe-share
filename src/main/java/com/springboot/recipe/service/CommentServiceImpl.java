package com.springboot.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.recipe.exception.RecipeNotFoundException;
import com.springboot.recipe.model.Comment;
import com.springboot.recipe.model.Recipe;
import com.springboot.recipe.repository.CommentRepo;
import com.springboot.recipe.repository.RecipeRepo;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    RecipeRepo recipeRepo;

    @Override
    public Comment addComment(Long recipeId, Comment comment) {
        Recipe recipe = recipeRepo.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + recipeId));
        comment.setRecipe(recipe);
        Comment savedComment = commentRepo.save(comment);
        recipe.getComments().add(savedComment);
        return savedComment;
    }

    @Override
    public List<Comment> getCommentsForRecipe(Long recipeId) {
        recipeRepo.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + recipeId));
        return commentRepo.findByRecipeId(recipeId);
    }
}
