package com.springboot.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.recipe.model.Recipe;
import com.springboot.recipe.service.RecipeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long id) {
        try {
            Recipe recipe = recipeService.getRecipeById(id);
            return ResponseEntity.status(200).body(recipe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting recipe: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getRecipes() {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            return ResponseEntity.status(200).body(recipes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting recipes: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveRecipe(@RequestBody Recipe entity) {
        try {
            Recipe recipe = recipeService.saveRecipe(entity);
            return ResponseEntity.status(201).body(recipe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving recipe: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long id, @RequestBody Recipe entity) {
        try {
            Recipe recipe = recipeService.updateRecipe(id, entity);
            return ResponseEntity.status(200).body(recipe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating recipe: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        try {
            recipeService.deleteRecipe(id);
            return ResponseEntity.status(200).body("Recipe deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting recipe: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchRecipes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String ingredient,
            @RequestParam(required = false) String category) {
        try {
            List<Recipe> recipes = recipeService.searchRecipes(name, ingredient, category);
            if (recipes.isEmpty()) {
                return ResponseEntity.status(200).body("No recipes found for the given criteria.");
            }
            return ResponseEntity.status(200).body(recipes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error searching recipes: " + e.getMessage());
        }
    }

}
