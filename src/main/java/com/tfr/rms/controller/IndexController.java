package com.tfr.rms.controller;

import com.tfr.rms.config.Routes;
import com.tfr.rms.config.Views;
import com.tfr.rms.model.Recipe;
import com.tfr.rms.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * Created by Erik on 9/1/2016.
 */

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = Routes.INDEX,
                    method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        logger.debug("hitting endpoint: " + Routes.INDEX);
        return Views.INDEX;
    }

    @RequestMapping(value= Routes.RECIPE_API,
                    method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String api() {
        logger.debug("hitting endpoint: " + Routes.RECIPE_API);
        return Views.API;
    }
}
