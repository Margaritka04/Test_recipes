package com.mirea.kt.recipes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import java.util.ArrayList;

public class DBManager {
    private SQLiteOpenHelper sqLiteHelper;

    public DBManager(SQLiteOpenHelper sqLiteHelper){
        this.sqLiteHelper = sqLiteHelper;
    }

    public boolean saveRecipeToDatabase(Recipe recipe){
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", recipe.getName());
        cv.put("ingredients", recipe.getIngredients());
        cv.put("cookingTime", recipe.getCookingTime());
        cv.put("methodOfPreparation", recipe.getMethodOfPreparation());

        long rowId = db.insert("TABLE_RECIPES",null,cv);
        cv.clear();
        db.close();
        return rowId != -1;
    }

    public ArrayList<Recipe> loadAllRecipesFromDatabase(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_RECIPES", null, null, null,null,null,null);
        if (dbCursor.moveToFirst()){
            do {
                String name = dbCursor.getString(dbCursor.getColumnIndexOrThrow("name"));
                String ingredients = dbCursor.getString(dbCursor.getColumnIndexOrThrow("ingredients"));
                String cookingTime = dbCursor.getString(dbCursor.getColumnIndexOrThrow("cookingTime"));
                String MethodOfPreparation = dbCursor.getString(dbCursor.getColumnIndexOrThrow("methodOfPreparation"));
//                int avatar = dbCursor.getInt(dbCursor.getColumnIndexOrThrow("Иконка рецепта"));
            }while (dbCursor.moveToNext());
        }
        dbCursor.close();
        db.close();
        return recipes;
    }
}