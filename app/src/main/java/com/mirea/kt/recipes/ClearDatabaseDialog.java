package com.mirea.kt.recipes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;



public class ClearDatabaseDialog extends DialogFragment {

    private final DBManager dbManager;

    public ClearDatabaseDialog(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Вы уверены, что хотите очистить базу данных?")
                .setPositiveButton("Да", (dialog, which) -> {
                    dbManager.clearDatabase();
                    dismiss();
                })
                .setNegativeButton("Нет", (dialog, which) -> dismiss());
        return builder.create();
    }
}