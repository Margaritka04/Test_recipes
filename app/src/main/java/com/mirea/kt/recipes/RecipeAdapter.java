package com.mirea.kt.recipes;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    interface OnRecipeClickListener{
        void onRecipeClick(Recipe recipe, int position);
    }
    private ArrayList<Recipe> recipes;
    private OnRecipeClickListener onRecipeClickListener;
    public RecipeAdapter(ArrayList<Recipe> recipes, OnRecipeClickListener onRecipeClickListener){
        this.recipes = recipes;
        this.onRecipeClickListener = onRecipeClickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameView;
        private final ImageView recipeImage;
//        private final ImageButton shareButton;

        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.name);
            recipeImage = view.findViewById(R.id.recipe_image);
//            shareButton = view.findViewById(R.id.btnShare);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        holder.recipeImage.setImageURI(Uri.parse(recipe.getAvatar()));
        holder.nameView.setText(String.format("%s", recipe.getName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecipeClickListener.onRecipeClick(recipe, holder.getAdapterPosition());
            }
        });

//        holder.itemView.setOnClickListener(v -> {onShareButtonRecipeClickListener.onShareButtonClick(recipe, position);});
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("text/plain");
//            String text = "Название: " + recipe.getName() + "\n"
//                    + "Ингредиенты: " + recipe.getIngredients() + "\n"
//                    + "Метод приготовления: " + recipe.getMethodOfPreparation() + "\n"
//                    + "Время приготовления: " + recipe.getCookingTime();
//            intent.putExtra(Intent.EXTRA_TEXT, text);
//            String title = "Поделиться рецептом";
//            v.getContext().startActivity(Intent.createChooser(intent, title));
//        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}