package com.tfr.rms.service;

import com.tfr.rms.dao.RecipeRepository;
import com.tfr.rms.model.Recipe;
import com.tfr.rms.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Erik on 9/3/2016.
 */

@Service
public class RecipeServiceImpl implements RecipeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe insertRecipe(Recipe recipe) {
        logger.debug("Saving recipe: " + recipe.toString());
        Recipe recipeOut = recipeRepository.insert(recipe);
        logger.debug("Inserted recipe: " + recipeOut.toString());
        return recipeOut;
    }

    @Override
    public Recipe findRecipeById(String id) {
        logger.debug("Reading recipe by id: " + id);
        Recipe recipe = recipeRepository.findOne(id);
        logger.debug("Found recipe: " + recipe.toString());
        return recipe;
    }

    @Override
    public List<Recipe> findRecipesByName(String name) {
        logger.debug("Reading recipe by name: " + name);
        List<Recipe> results = recipeRepository.findByName(name);
        logResultSet(results);
        return results;
    }

    @Override
    public List<Recipe> findAll() {
        logger.debug("Reading all recipes");
        List<Recipe> results = recipeRepository.findAll();
        logResultSet(results);
        return results;
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        logger.debug("Updating recipe: " + recipe.toString());
        Recipe recipeOut = recipeRepository.save(recipe);
        logger.debug("Updated recipe: " + recipeOut.toString());
        return recipeOut;
    }

    @Override
    public ResponseMessage deleteRecipe(Recipe recipe) {
        logger.debug("Deleting recipe: " + recipe.toString());
        recipeRepository.delete(recipe);
        logger.debug("Recipe deleted");
        return new ResponseMessage("Recipe deleted: " + recipe.toString());
    }

    @Override
    public Map<String, String> getRecipeNames() {
        Map<String, String> namesMap = new HashMap<>();
        List<Recipe> recipes = findAll();
        recipes.forEach((r) ->
                namesMap.put(r.getId(),r.getName())
        );
        return namesMap;
    }

    private void logResultSet(List<Recipe> results) {
        if(results.size()>0) {
            StringBuilder sb = new StringBuilder();
            for(Recipe r : results) {
                sb.append(r.toString() + "\n");
            }
            logger.debug(sb.toString());
        } else {
            logger.debug("No results Returned");
        }
    }
}
