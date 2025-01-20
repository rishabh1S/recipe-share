package com.springboot.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.recipe.model.Recipe;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE " +
            "(?1 IS NULL OR r.title ILIKE %?1%) AND " +
            "(?2 IS NULL OR EXISTS (SELECT 1 FROM r.ingredients i WHERE i ILIKE %?2%)) AND " +
            "(?3 IS NULL OR r.category ILIKE %?3%)")
    List<Recipe> searchRecipes(String name, String ingredient, String category);

}
