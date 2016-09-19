package com.tfr.rms.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfr.rms.model.Ingredient;
import com.tfr.rms.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Erik on 9/5/2016.
 */
public class RecipeTestHelper {

    private static final Logger logger = LoggerFactory.getLogger(RecipeTestHelper.class);

    public static Recipe getTestRecipe(String name) {
        Recipe recipe = new Recipe();

        recipe.setName(name);
        recipe.setDescription("This is a test recipe");
        recipe.setPrepTime(15);
        recipe.setCookTime(60);

        Ingredient i1 = new Ingredient();
        i1.setName("Test Ingredient");
        i1.setAmount(1);
        i1.setMeasure("Tbsp");
        i1.setNotes("notes");

        recipe.setIngredients(Arrays.asList(i1));

        recipe.setDirections("directions here");

        return recipe;
    }

    public static String recipeToJson(Recipe recipe) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(recipe);
        logger.debug("Converted recipe to json: " + json);
        return json;

    }

}
