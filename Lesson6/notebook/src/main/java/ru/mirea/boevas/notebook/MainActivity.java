package ru.mirea.boevas.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = ((EditText) findViewById(R.id.editTextFileName)).getText().toString();
                String quote = ((EditText) findViewById(R.id.editTextQuote)).getText().toString();
                saveToFile(fileName, quote);
            }
        });

        Button buttonLoad = findViewById(R.id.buttonLoad);
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = ((EditText) findViewById(R.id.editTextFileName)).getText().toString();
                String quote = loadFromFile(fileName);
                ((EditText) findViewById(R.id.editTextQuote)).setText(quote);
            }
        });

        // Save quotes of famous people
        saveToFile("Mark Twain.txt", "The secret of getting ahead is getting started.\n");
        saveToFile("Albert Einstein.txt", "Try not to become a man of success, but rather try to become a man of value.\n");
    }

    private void saveToFile(String fileName, String quote) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(quote);
            writer.close();
            Log.w("ExternalStorage", String.format("Saved to file %s successful", fileName));
        } catch (Exception e) {
            Log.w("ExternalStorage", String.format("Save to file %s failed", fileName));
        }
    }

    private String loadFromFile(String fileName) {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            Log.w("ExternalStorage", String.format("Load from file %s failed", fileName));
            return "";
        }
    }

}