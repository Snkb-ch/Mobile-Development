package ru.mirea.boevas.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private TextView textViewStudent;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;

    public void onMyButtonClick(View view) {

            Toast.makeText(this, "second", Toast.LENGTH_SHORT).show();
//            change checkbox state
            checkBox.setChecked(!checkBox.isChecked());
            textViewStudent.setText("Second");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewStudent = findViewById(R.id.textViewStudent);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);
        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку № 5");

            }
        };


        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);





        checkBox = findViewById(R.id.checkBox);




    }
}
