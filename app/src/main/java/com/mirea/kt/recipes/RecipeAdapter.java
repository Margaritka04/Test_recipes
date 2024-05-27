package com.mirea.kt.recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public RecipeAdapter(ArrayList<Recipe> recipes){
        this.recipes = recipes;
    }
    public RecipeAdapter(ArrayList<Recipe> recipes, OnRecipeClickListener onRecipeClickListener){
        this.recipes = recipes;
        this.onRecipeClickListener = onRecipeClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nameView;

        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.name);
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
        holder.nameView.setText(String.format("%s", recipe.getName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecipeClickListener.onRecipeClick(recipe, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
