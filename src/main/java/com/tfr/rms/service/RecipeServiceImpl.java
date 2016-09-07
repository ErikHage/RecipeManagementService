package com.tfr.rms.service;

import com.tfr.rms.dao.RecipeRepository;
import com.tfr.rms.model.Recipe;
import com.tfr.rms.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Recipe> findRecipeByName(String name) {
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
        logger.debug("Saved recipe: " + recipeOut.toString());
        return recipeOut;
    }

    @Override
    public ResponseMessage deleteRecipe(Recipe recipe) {
        logger.debug("Deleting recipe: " + recipe.toString());
        recipeRepository.delete(recipe);
        logger.debug("Recipe deleted");
        return new ResponseMessage("Recipe deleted: " + recipe.toString());
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
