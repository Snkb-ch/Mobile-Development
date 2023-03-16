package ru.mirea.boevas.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        EditText textEdit = (EditText) findViewById(R.id.text);
        String text = textEdit.getText().toString();
        int count = text.length();
        String toastText = "Boev A.S BSBO-01-20 count = " + count;
        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();



    }
}