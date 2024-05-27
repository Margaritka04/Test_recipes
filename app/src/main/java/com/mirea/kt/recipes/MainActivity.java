package com.mirea.kt.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.etLogin);
        password = findViewById(R.id.etPassword);
        buttonStart = findViewById(R.id.btnStart);
        buttonStart.setOnClickListener(v -> {
            String lgn = login.getText().toString();
            String pwd = password.getText().toString();
            if (!lgn.isEmpty() && !pwd.isEmpty()) {
                if (lgn.equals("admin") && pwd.equals("root")) {
                    Intent intent = new Intent(getApplicationContext(), RecipesList.class);
                    startActivity(intent);
                } else {
                    String server = " https://android-for-students.ru/";
                    String serverPath = "/coursework/login.php";
                    HashMap<String, String> map = new HashMap<>();
                    map.put("lgn", lgn);
                    map.put("pwd", pwd);
                    map.put("g", "RIBO-04-22");
                    HTTPRunnable httpRunnable = new HTTPRunnable(server + serverPath, map);
                    Thread th = new Thread(httpRunnable);
                    th.start();
                    try{
                        th.join();
                    }catch(InterruptedException ex){
                        ex.printStackTrace();
                    }finally{
                        try {
                            JSONObject jsonObject = new JSONObject(httpRunnable.getResponseBody());
                                if (jsonObject.getInt("result_code") == 1) {
                                    Log.i("Authorization", "Вход успешно выполнен");
                                    Intent intent = new Intent(getApplicationContext(), RecipesList.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Неверный логин или пароль", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException | NullPointerException ignore) {

                            }
                        } /*else {
                            Toast.makeText(getApplicationContext(), "Не удалось подключиться к серверу", Toast.LENGTH_LONG).show();
                        }*/
                    } /*catch (IOException exception) {
                        Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
                    }*/
                }
            else {
                Toast.makeText(getApplicationContext(), "Поля не должны быть пустыми!", Toast.LENGTH_LONG).show();
            }
        });
    }
}