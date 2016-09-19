package com.tfr.rms.service;

import com.tfr.rms.model.Recipe;
import com.tfr.rms.model.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Erik on 9/2/2016.
 */
public interface RecipeService {

    Recipe insertRecipe(Recipe recipe);
    Recipe findRecipeById(String id);
    List<Recipe> findRecipesByName(String name);
    List<Recipe> findAll();
    Recipe updateRecipe(Recipe recipe);
    ResponseMessage deleteRecipe(Recipe recipe);

    Map<String, String> getRecipeNames();

}
