package com.tfr.rms.service;

import com.tfr.rms.model.Recipe;
import com.tfr.rms.model.ResponseMessage;

import java.util.List;

/**
 * Created by Erik on 9/2/2016.
 */
public interface RecipeService {

    Recipe insertRecipe(Recipe recipe);
    List<Recipe> findRecipeByName(String name);
    List<Recipe> findAll();
    Recipe updateRecipe(Recipe recipe);
    ResponseMessage deleteRecipe(Recipe recipe);

}
