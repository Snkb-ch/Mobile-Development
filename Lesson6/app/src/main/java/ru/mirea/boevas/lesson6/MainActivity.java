package ru.mirea.boevas.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText groupEditText;
    EditText numberEditText;
    EditText favoriteMovieEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        String group = sharedPref.getString("group", "XXXX-XX-XX");
        int number = sharedPref.getInt("number", 0);
        String favoriteMovie = sharedPref.getString("favorite_movie", "Movie");

        EditText groupEditText = findViewById(R.id.groupEditText);
        EditText numberEditText = findViewById(R.id.numberEditText);
        EditText favoriteMovieEditText = findViewById(R.id.favoriteMovieEditText);

        groupEditText.setText(group);
        numberEditText.setText(Integer.toString(number));
        favoriteMovieEditText.setText(favoriteMovie);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сохраняем данные в SharedPreferences
                SharedPreferences sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("group", groupEditText.getText().toString());
                editor.putInt("number", Integer.parseInt(numberEditText.getText().toString()));
                editor.putString("favorite_movie", favoriteMovieEditText.getText().toString());
                editor.apply();
            }
        });
    }

}
