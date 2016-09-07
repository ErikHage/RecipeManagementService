package com.tfr.rms.config;

/**
 * Created by Erik on 8/31/2016.
 */
public interface Routes {

    String INDEX = "/";
    String API = "/api";
    String SERVICE_INFO = "/api/service/info";

    String RECIPE_CREATE = "/api/recipe/create";

    String RECIPE_READ_BY_NAME = "/api/recipe/find/name/{recipeName}";
    String RECIPE_READ_ALL = "/api/recipe/find/all";

    String RECIPE_UPDATE = "/api/recipe/update";
    String RECIPE_DELETE = "/api/recipe/delete";

}
