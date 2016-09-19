package com.tfr.rms.controller;

import com.tfr.rms.config.Constants;
import com.tfr.rms.config.Routes;
import com.tfr.rms.model.Recipe;
import com.tfr.rms.model.ResponseMessage;
import com.tfr.rms.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * Created by Erik on 9/2/2016.
 */

@RestController
public class RecipeRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = Routes.API_RECIPE_CREATE,
                    consumes = Constants.APPLICATION_JSON,
                    produces = Constants.APPLICATION_JSON,
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        logger.debug("endpoint: " + Routes.API_RECIPE_CREATE);
        return recipeService.insertRecipe(recipe);
    }

    @RequestMapping(value = Routes.API_RECIPE_FIND_BY_NAME,
            produces = Constants.APPLICATION_JSON,
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> findRecipeByName(@PathVariable String name) {
        logger.debug("endpoint: " + Routes.API_RECIPE_FIND_BY_NAME.replace("{name}", name));
        return recipeService.findRecipesByName(name);
    }

    @RequestMapping(value = Routes.API_RECIPE_FIND_BY_ID,
            produces = Constants.APPLICATION_JSON,
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Recipe findRecipeById(@PathVariable String id) {
        logger.debug("endpoint: " + Routes.API_RECIPE_FIND_BY_ID.replace("{id}", id));
        return recipeService.findRecipeById(id);
    }

    @RequestMapping(value = Routes.API_RECIPE_FIND_ALL,
            produces = Constants.APPLICATION_JSON,
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> findAllRecipes() {
        logger.debug("endpoint: " + Routes.API_RECIPE_FIND_ALL);
        return recipeService.findAll();
    }

    @RequestMapping(value = Routes.API_RECIPE_UPDATE,
            consumes = Constants.APPLICATION_JSON,
            produces = Constants.APPLICATION_JSON,
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Recipe updateRecipe(@RequestBody Recipe recipe) {
        logger.debug("endpoint: " + Routes.API_RECIPE_UPDATE);
        return recipeService.updateRecipe(recipe);
    }

    @RequestMapping(value = Routes.API_RECIPE_DELETE,
            consumes = Constants.APPLICATION_JSON,
            produces = Constants.APPLICATION_JSON,
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage deleteRecipe(@RequestBody Recipe recipe) {
        logger.debug("endpoint: " + Routes.API_RECIPE_DELETE);
        return recipeService.deleteRecipe(recipe);
    }

}
