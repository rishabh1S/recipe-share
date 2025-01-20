package com.springboot.recipe.service;

import java.util.List;

import com.springboot.recipe.model.Recipe;

public interface RecipeService {
    public List<Recipe> getAllRecipes();

    public Recipe getRecipeById(long id);

    public Recipe saveRecipe(Recipe recipe);

    public Recipe updateRecipe(long id, Recipe recipe);

    public void deleteRecipe(long id);

    public List<Recipe> searchRecipes(String name, String ingredient, String category);
}
