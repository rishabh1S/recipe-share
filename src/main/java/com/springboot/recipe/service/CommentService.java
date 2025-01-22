package com.springboot.recipe.service;

import java.util.List;

import com.springboot.recipe.model.Comment;

public interface CommentService {
    public Comment addComment(Long recipeId, Comment comment);

    public List<Comment> getCommentsForRecipe(Long recipeId);
}
