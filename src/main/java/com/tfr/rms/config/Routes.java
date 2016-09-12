package com.tfr.rms.config;

/**
 *
 * Created by Erik on 8/31/2016.
 */
public interface Routes {

    /* Views */
    String INDEX = "/";
    String RECIPE_API = "/api";
    String INPUT_RECIPE = "/input";
    String FIND_RECIPE = "/find";
    String UPDATE_RECIPE = "/update";

    /* Rest API */
    String API_SERVICE_INFO = "/api/service/info";

    String API_RECIPE_CREATE = "/api/recipe/create";
    String API_RECIPE_UPDATE = "/api/recipe/update";
    String API_RECIPE_DELETE = "/api/recipe/delete";

    String API_RECIPE_FIND_BY_NAME = "/api/recipe/find/name/{recipeName}";
    String API_RECIPE_FIND_ALL = "/api/recipe/find/all";


}
