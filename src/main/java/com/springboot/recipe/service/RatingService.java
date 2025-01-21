package com.springboot.recipe.service;

import com.springboot.recipe.model.Rating;

public interface RatingService {
    public Rating rateRecipe(Long recipeId, Rating rating);
}
