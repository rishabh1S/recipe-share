package com.springboot.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.recipe.model.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByRecipeId(Long recipeId);
}
