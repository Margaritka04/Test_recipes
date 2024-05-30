package com.mirea.kt.recipes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyAppSQLiteHelper extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME = "dataManager";
    public static final int DATABASE_VERSION = 1;
    //    public static final String TABLE_RECIPES = "";
    public static final String KEY_ID = "id";
    public static final String KEY_IMG_URL = "Img";
    public MyAppSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public static final String CREATE_TABLE = "create table TABLE_RECIPES (KEY_ID integer primary key autoincrement,name text,ingredients text,cookingTime integer,methodOfPreparation text);";
//    public static final String DROP_TABLE = "Drop table if exists " + TABLE_RECIPES + "";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void deleteEntry(long row){
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        sqLiteDatabase.delete(TABLE_RECIPES, KEY_ID + "=" + row, null);
    }
}