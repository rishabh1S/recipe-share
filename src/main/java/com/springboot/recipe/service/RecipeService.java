package com.springboot.recipe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.recipe.model.Recipe;

@Service
public interface RecipeService {
    public List<Recipe> getAllRecipes();

    public Recipe getRecipeById(long id);

    public Recipe saveRecipe(Recipe recipe);

    public Recipe updateRecipe(long id, Recipe recipe);

    public void deleteRecipe(long id);
}
