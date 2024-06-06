package com.mirea.kt.recipes;

import android.app.Person;
import android.net.Uri;

public class Recipe {
    private int recipeId;
    private String name;
    private String ingredients;
    private int cookingTime;
    private String methodOfPreparation;
    private String avatar;


    public Recipe(String name, String ingredients, int cookingTime, String methodOfPreparation, String avatar) {
        this.name = name;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
        this.methodOfPreparation = methodOfPreparation;
        this.avatar = avatar;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getMethodOfPreparation() {
        return methodOfPreparation;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() { return avatar; }
}