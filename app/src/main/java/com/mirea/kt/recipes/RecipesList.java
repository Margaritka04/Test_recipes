package com.mirea.kt.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipesList extends AppCompatActivity implements RecipeAdapter.OnRecipeClickListener {
    public void onRecipeClick(Recipe recipe, int position){
        Toast.makeText(this, "click on " +
                recipe.getName(), Toast.LENGTH_LONG).show();
    }

    private DBManager db = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));
    private ArrayList<Recipe> recipes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();

        if (ab != null) {
            ab.setHomeButtonEnabled(true);
            ab.setDisplayHomeAsUpEnabled(true);

        }

        Button btnUpdate = (Button) tb.findViewById(R.id.updating);
        btnUpdate.setOnClickListener(v -> {
            recipes = db.loadAllRecipesFromDatabase();
        });

        recipes.add(new Recipe("Cake", "мука, клубника, персик", 20, "уаоиысгы" ));
        recipes.add(new Recipe("Cake", "мука, клубника, персик", 20, "уаоиысгы"));
        recipes.add(new Recipe("Cake", "мука, клубника, персик", 20, "уаоиысгы"));

        RecyclerView rcView = findViewById(R.id.recyclerView);
        RecipeAdapter adapter = new RecipeAdapter(recipes, this);
        rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcView.setAdapter(adapter);
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
        if (id == R.id.updating){
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}