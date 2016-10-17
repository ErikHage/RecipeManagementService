package com.tfr.rms.controller;

import com.tfr.rms.config.Routes;
import com.tfr.rms.config.Views;
import com.tfr.rms.model.Ingredient;
import com.tfr.rms.model.Recipe;
import com.tfr.rms.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Erik on 9/11/2016.
 */

@Controller
public class RecipeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = Routes.INPUT_RECIPE,
                    method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String getBlankRecipeForm(Model model) {
        logger.debug("hitting endpoint: " + Routes.INPUT_RECIPE);
        setCommonAttributes(model);

        Recipe recipe = getEmptyRecipe();
        model.addAttribute("recipe", recipe);
        model.addAttribute("statusMessage", "Please fill out the below fields");

        return Views.EDIT_RECIPE;
    }

    @RequestMapping(value = Routes.INPUT_ADD_ROW,
                    method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String addIngredientToRecipe(@ModelAttribute("recipe") Recipe recipe, Model model) {
        logger.debug("hitting endpoint: " + Routes.INPUT_ADD_ROW);
        setCommonAttributes(model);

        addIngredient(recipe);

        model.addAttribute("recipe", recipe);
        model.addAttribute("statusMessage", "Please fill out the below fields");

        return Views.EDIT_RECIPE;
    }

    @RequestMapping(value = Routes.INPUT_REMOVE_ROW + "{index}",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String addIngredientToRecipe(@ModelAttribute("recipe") Recipe recipe,
                                        @PathVariable("index") int index,
                                        Model model) {
        logger.debug("hitting endpoint: " + Routes.INPUT_REMOVE_ROW + index);
        setCommonAttributes(model);

        removeIngredient(recipe, index);

        model.addAttribute("recipe", recipe);
        model.addAttribute("statusMessage", "Please fill out the below fields");

        return Views.EDIT_RECIPE;
    }

    @RequestMapping(value = Routes.INPUT_RECIPE,
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String insertRecipe(@ModelAttribute("recipe") Recipe recipe,  Model model) {
        logger.debug("hitting endpoint: " + Routes.INPUT_RECIPE);
        setCommonAttributes(model);

        Recipe recipeOut = recipeService.insertRecipe(recipe);

        if(recipeOut != null) {
            logger.debug("Recipe created: " + recipeOut.toString());
            model.addAttribute("recipe", recipeOut);
            model.addAttribute("statusMessage", "Recipe Created!");
        } else {
            logger.error("Unable to create recipe");
            model.addAttribute("recipe", recipe);
            model.addAttribute("statusMessage", "Failed to save recipe");
        }

        return Views.EDIT_RECIPE;
    }

    @RequestMapping(value= Routes.FIND_RECIPE,
    method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String findRecipe(Model model) {
        logger.debug("hitting endpoint: " + Routes.FIND_RECIPE);
        setCommonAttributes(model);

        Map<String,String> recipeNames = recipeService.getRecipeNames();

        Recipe recipe = new Recipe();
        model.addAttribute("recipeNames", recipeNames);
        model.addAttribute("recipe", recipe);

        return Views.FIND_RECIPE;
    }

    @RequestMapping(value= Routes.FIND_RECIPE,
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String findRecipeLoad(@ModelAttribute("recipe") Recipe recipe, Model model) {
        logger.debug("hitting endpoint: " + Routes.FIND_RECIPE);
        logger.debug("Attempting to load recipe with id = " + recipe.getId());
        setCommonAttributes(model);

        Recipe recipeOut = recipeService.findRecipeById(recipe.getId());
        model.addAttribute("recipe", modifyRecipeForDisplay(recipeOut));

        return Views.VIEW_RECIPE;
    }

    @RequestMapping(value= Routes.UPDATE_RECIPE_LOAD,
                    method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String updateRecipe(@PathVariable("id") String id, Model model) {
        logger.debug("hitting endpoint: " + Routes.UPDATE_RECIPE_LOAD.replace("{id}", id));
        setCommonAttributes(model);

        Recipe recipe = recipeService.findRecipeById(id);
        if(recipe != null){
            logger.debug("Loaded recipe:" + recipe.toString());
            model.addAttribute("recipe", recipe);
            model.addAttribute("statusMessage", "Ready to edit");
        } else {
            logger.debug("Unable to load recipe with id = " + id);
            model.addAttribute("recipe", new Recipe());
            model.addAttribute("statusMessage", "Did not find recipe with name = " + id);
        }

        return Views.EDIT_RECIPE;
    }

    @RequestMapping(value= Routes.UPDATE_RECIPE,
            method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String updateRecipe(@ModelAttribute("recipe") Recipe recipe, Model model) {
        logger.debug("hitting endpoint: " + Routes.UPDATE_RECIPE);
        setCommonAttributes(model);

        Recipe recipeOut = recipeService.updateRecipe(recipe);

        if(recipeOut != null) {
            logger.debug("Recipe updated: " + recipeOut.toString());
            model.addAttribute("recipe", recipeOut);
            model.addAttribute("statusMessage", "Recipe Updated!");
        } else {
            logger.error("Unable to update recipe");
            model.addAttribute("recipe", recipe);
            model.addAttribute("statusMessage", "Failed to update recipe");
        }

        return Views.EDIT_RECIPE;
    }

    private void setCommonAttributes(Model model) {
        model.addAttribute("statusMessage", "");
    }

    private Recipe getEmptyRecipe() {
        Recipe recipe = new Recipe();
        recipe.setIngredients(Collections.singletonList(new Ingredient()));
        return recipe;
    }

    private void addIngredient(Recipe recipe) {
        logger.debug("Adding ingredient to recipe");
        if(recipe.getIngredients() == null) {
            recipe.setIngredients(Collections.singletonList(new Ingredient()));
        } else {
            recipe.getIngredients().add(new Ingredient());
        }
    }

    private void removeIngredient(Recipe recipe, int index) {
        if(recipe.getIngredients().size() > index) {
            logger.debug("Removing ingredient: " + recipe.getIngredients().get(index));
            recipe.getIngredients().remove(index);
        } else {
            logger.warn("Invalid index to remove ingredient");
        }
    }

    private Recipe modifyRecipeForDisplay(Recipe recipe) {
        recipe.setDirections(recipe.getDirections().replace("\n","<br/>"));
        return recipe;
    }
}
