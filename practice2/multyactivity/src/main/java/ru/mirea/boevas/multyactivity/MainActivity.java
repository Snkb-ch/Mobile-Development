package ru.mirea.boevas.multyactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickNewActivity(View view) {

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String text = editText.getText().toString();

        intent.putExtra("key", text);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
    @Override
    protected void onPause() {

        super.onPause();
    }



}