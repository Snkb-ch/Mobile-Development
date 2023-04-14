package ru.mirea.boevas.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import android.os.Handler;
import android.widget.EdgeEffect;
import android.widget.EditText;

import ru.mirea.boevas.looper.databinding.ActivityMainBinding;

public	class	MainActivity	extends	AppCompatActivity {
    private EditText Age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Age = binding.editTextAge;

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {


                Log.d(MainActivity.class.getSimpleName(), "Task execute. Delay and age in sec: " + msg.getData().getString("result"));


            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = Age.getText().toString();

                int delaySeconds = Integer.parseInt(text);
                long delayMillis = delaySeconds * 1000;


                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("KEY", text);
                msg.setData(bundle);
                Log.d(MainActivity.class.getSimpleName(), "Send message. Delay and age in sec: " + text);

                myLooper.mHandler.sendMessageDelayed(msg, delayMillis);



            }
        });
    }
}