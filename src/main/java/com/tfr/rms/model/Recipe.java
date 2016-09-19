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
    private String id;

    private String name;
    private String description;

    private double prepTime;
    private double cookTime;
    private double yield;

    private List<Ingredient> ingredients;

    private String directions;

    public Recipe() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(double prepTime) {
        this.prepTime = prepTime;
    }

    public double getCookTime() {
        return cookTime;
    }

    public void setCookTime(double cookTime) {
        this.cookTime = cookTime;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

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
