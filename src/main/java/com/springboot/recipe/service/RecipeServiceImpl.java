package com.springboot.recipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.recipe.model.Recipe;
import com.springboot.recipe.repository.RecipeRepo;

public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }

    @Override
    public Recipe getRecipeById(long id) {
        return recipeRepo.findById(id).orElse(null);
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    @Override
    public Recipe updateRecipe(long id, Recipe recipe) {
        return recipeRepo.findById(id).map(r -> {
            r.setTitle(recipe.getTitle());
            r.setDescription(recipe.getDescription());
            r.setIngredients(recipe.getIngredients());
            return recipeRepo.save(r);
        }).orElse(null);
    }

    @Override
    public void deleteRecipe(long id) {
        if (recipeRepo.existsById(id)) {
            recipeRepo.deleteById(id);
        }
    }

}
