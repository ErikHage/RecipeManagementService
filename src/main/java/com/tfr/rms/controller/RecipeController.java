package com.tfr.rms.controller;

import com.tfr.rms.config.Routes;
import com.tfr.rms.config.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * Created by Erik on 9/11/2016.
 */

@Controller
public class RecipeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value= Routes.INPUT_RECIPE)
    @ResponseStatus(HttpStatus.OK)
    public String inputRecipe() {
        logger.debug("hitting endpoint: " + Routes.INPUT_RECIPE);
        return Views.ADD_RECIPE;
    }

    @RequestMapping(value= Routes.FIND_RECIPE)
    @ResponseStatus(HttpStatus.OK)
    public String findRecipe() {
        logger.debug("hitting endpoint: " + Routes.FIND_RECIPE);
        return Views.FIND_RECIPE;
    }

    @RequestMapping(value= Routes.UPDATE_RECIPE)
    @ResponseStatus(HttpStatus.OK)
    public String updateRecipe() {
        logger.debug("hitting endpoint: " + Routes.UPDATE_RECIPE);
        return Views.UPDATE_RECIPE;
    }
}
