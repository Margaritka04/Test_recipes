package com.mirea.kt.recipes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class NewRecipe extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText ingredients;
    private EditText cookingTime;
    private EditText methodOfPreparation;
    private Button btnAddRecipe;
    private Button btnCancel;
    private EditText editTextName, editTextIngredients, editTextCookingTime, editTextMethodOfPreparation;
    private ImageView imageViewAvatar;
    private DBManager dbManager;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_recipe);
        name = findViewById(R.id.etName);
        ingredients = findViewById(R.id.etIngredients);
        cookingTime = findViewById(R.id.etCookingTime);
        methodOfPreparation = findViewById(R.id.etMethodOfPreparation);
        imageViewAvatar = findViewById(R.id.etAvatar);
        btnAddRecipe = findViewById(R.id.btnAddRecipe);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(v -> finish());
        btnAddRecipe.setOnClickListener(v -> {
            if (v.getId() == R.id.btnAddRecipe){
                if (this.dbManager != null){
                    String name = editTextName.getText().toString();
                    String ingredients = editTextIngredients.getText().toString();
                    String cookingTime = editTextCookingTime.getText().toString();
                    String methodOfPreparation = editTextMethodOfPreparation.getText().toString();

                    if (!name.isEmpty() && !ingredients.isEmpty() && !cookingTime.isEmpty() && !methodOfPreparation.isEmpty() && filePath != null){
//                        if (filePath == null) {
                        boolean result = dbManager.saveRecipeToDatabase(new Recipe(name, ingredients, Integer.parseInt(cookingTime), methodOfPreparation, filePath.toString()));
                        if (result) {
                            finish();
                            Toast.makeText(this, R.string.insert_success, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                        }
//                        } else {
//                            boolean result = dbManager.saveRecipeToDatabase(new Recipe(name, ingredients, Integer.parseInt(cookingTime), methodOfPreparation, filePath));
//                            if (result) {
//                                filePath = null;
//                                finish();
//                                Toast.makeText(this, R.string.insert_success, Toast.LENGTH_LONG).show();
//                            } else {
//                                Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
//                            }
//                        }
                    }else {
                        Toast.makeText(this, R.string.incorrect_value,Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        editTextName = findViewById(R.id.etName);
        editTextIngredients = findViewById(R.id.etIngredients);
        editTextCookingTime = findViewById(R.id.etCookingTime);
        editTextMethodOfPreparation = findViewById(R.id.etMethodOfPreparation);

        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));

        imageViewAvatar.setOnClickListener(v -> {
            selectImage();
        });

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLaunch.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null && result.getData().getData() != null) {
                    filePath = result.getData().getData();
                    imageViewAvatar.setImageURI(filePath);
                }
            });

    @Override
    public void onClick(View v) {}
}