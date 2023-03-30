package ru.mirea.boevas.lesson3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Получение переданного времени
        Intent intent = getIntent();
        String time = intent.getStringExtra("time");

        // Отображение времени в textView
        TextView textView = findViewById(R.id.textView);
        textView.setText("25: " + time);
    }
}
