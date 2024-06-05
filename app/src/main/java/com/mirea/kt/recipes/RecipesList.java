package com.mirea.kt.recipes;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipesList extends AppCompatActivity implements RecipeAdapter.OnRecipeClickListener {

    private DBManager dbManager;
    private Dialog dialog;
    public void onRecipeClick(Recipe recipe, int position){
        Toast.makeText(this, "click on " +
                recipe.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        Toolbar tb = findViewById(R.id.toolbar);
        dialog = new Dialog(this);
        dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));


        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView() {
        ArrayList<Recipe> recipes = dbManager.loadAllRecipesFromDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        RecipeAdapter doctorAdapter = new RecipeAdapter(recipes, this);
        recyclerView.setAdapter(doctorAdapter);
    }

    private void createClearDataBaseDialog() {
        dialog.setContentView(R.layout.confirm_clear_database_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button confirm_button = dialog.findViewById(R.id.confirm_button);


        RadioGroup radioGroup = dialog.findViewById(R.id.radio);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = dialog.findViewById(radioButtonId);
                if (radioButton.getText().toString().equals("Да")) {
                    dbManager.clearDatabase();
                    updateView();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipes_list_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            startActivity(new Intent(getApplicationContext(), NewRecipe.class));
            Toast.makeText(this, "Добавить новый рецепт", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.find) {
            Toast.makeText(this, "Найти рецепт", Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.exit) {
            finish();
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.clear_database) {
            createClearDataBaseDialog();
        }
        return super.onOptionsItemSelected(item);
    }
}