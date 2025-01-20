package com.springboot.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.recipe.model.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

}
