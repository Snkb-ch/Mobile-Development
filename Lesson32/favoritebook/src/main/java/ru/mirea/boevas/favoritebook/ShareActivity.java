package ru.mirea.boevas.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    private EditText editTextBookName;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);

        editTextBookName = findViewById(R.id.editTextBookName);
        buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userBook = editTextBookName.getText().toString();
                Intent intent = new Intent();
                intent.putExtra(MainActivity.USER_MESSAGE, userBook);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
