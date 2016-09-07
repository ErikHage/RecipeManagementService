package com.tfr.rms.model;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Recipe DTO class
 *
 * Created by Erik on 9/2/2016.
 */
public class Recipe {

    @Id
    public String id;

    public String name;
    public String description;

    public double prepTime;
    public double cookTime;
    public double yield;

    public List<Ingredient> ingredients;

    public String directions;

    public Recipe() {

    }

//    @Override
//    public String toString() {
//        return String.format(
//                "Recipe[id=%s, name='%s', description='%s']",
//                id, name, description);
//    }


    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prepTime=" + prepTime +
                ", cookTime=" + cookTime +
                ", yield=" + yield +
                ", ingredients=" + ingredients +
                ", directions='" + directions + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipe recipe = (Recipe) o;

        if (Double.compare(recipe.prepTime, prepTime) != 0) return false;
        if (Double.compare(recipe.cookTime, cookTime) != 0) return false;
        if (Double.compare(recipe.yield, yield) != 0) return false;
        if (id != null ? !id.equals(recipe.id) : recipe.id != null) return false;
        if (name != null ? !name.equals(recipe.name) : recipe.name != null) return false;
        if (description != null ? !description.equals(recipe.description) : recipe.description != null) return false;
        if (ingredients != null ? !ingredients.equals(recipe.ingredients) : recipe.ingredients != null) return false;
        return directions != null ? directions.equals(recipe.directions) : recipe.directions == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(prepTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cookTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yield);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (directions != null ? directions.hashCode() : 0);
        return result;
    }
}
