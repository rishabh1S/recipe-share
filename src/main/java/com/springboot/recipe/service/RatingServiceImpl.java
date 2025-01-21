package com.springboot.recipe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.recipe.exception.RecipeNotFoundException;
import com.springboot.recipe.exception.UserAlreadyRatedException;
import com.springboot.recipe.model.Rating;
import com.springboot.recipe.model.Recipe;
import com.springboot.recipe.repository.RatingRepo;
import com.springboot.recipe.repository.RecipeRepo;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private RecipeRepo recipeRepo;

    @Override
    public Rating rateRecipe(Long recipeId, Rating rating) {
        Recipe recipe = recipeRepo.findById(recipeId).orElseThrow(
                () -> new RecipeNotFoundException("Recipe not found with Id: " + recipeId));
        Optional<Rating> existingRating = ratingRepo.findByUserIdAndRecipeId(rating.getUserId(), recipeId);
        if (existingRating.isPresent()) {
            throw new UserAlreadyRatedException("User has already rated this recipe.");
        }
        rating.setRecipe(recipe);
        Rating savedRating = ratingRepo.save(rating);
        recipe.getRatings().add(savedRating);
        return savedRating;
    }
}
