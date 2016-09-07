package com.tfr.rms.dao;

import com.tfr.rms.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Dao for Recipes in the Mongo database
 *
 * Created by Erik on 9/3/2016.
 */

public interface RecipeRepository extends MongoRepository<Recipe, String> {

    List<Recipe> findByName(String name);

}
