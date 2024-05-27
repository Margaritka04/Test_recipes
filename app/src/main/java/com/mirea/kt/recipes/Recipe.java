package com.mirea.kt.recipes;

import android.app.Person;

public class Recipe {
    private String name;
    private String ingredients;
    private int cookingTime;
    private String methodOfPreparation;
    private String avatar;

    public Recipe(String name, String ingredients, int cookingTime, String methodOfPreparation ) {
        this.name = name;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
        this.methodOfPreparation = methodOfPreparation;

    }

    public Recipe(String name) {
        this.name = name;
    }


    public String getMethodOfPreparation() {
        return methodOfPreparation;
    }

    public void setMethodOfPreparation(String methodOfPreparation) {
        this.methodOfPreparation = methodOfPreparation;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
