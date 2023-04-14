package ru.mirea.boevas.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.mirea.boevas.thread.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding  binding; // declare the binding object
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // initialize the binding object
        setContentView(binding.getRoot()); // set the content view using the binding object
        Button buttonMirea = binding.buttonMirea; // get a reference to the button using the binding object
        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText( mainThread.getName());
// Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-01-20, НОМЕР ПО СПИСКУ: 5, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Марсианин");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));






        binding.buttonMirea.setOnClickListener(new	View.OnClickListener()	{
            @Override
            public	void	onClick(View	v)	{



                new	Thread(new	Runnable()	{

                    public	void run()	{


                        int	numberThread	=	counter++;
                        Log.d("ThreadProject",	String.format("Запущен	поток	№	%d	студентом	группы	№	%s	номер	по списку	№	%d	",	numberThread,	"БСБО-ХХ-ХХ",	-1));
                        long	endTime	=	System.currentTimeMillis()	+	20	*	1000;
                        while	(System.currentTimeMillis()	<	endTime)	{
                            synchronized	(this)	{
                                try	{
                                    wait(endTime	- System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(),	"Endtime:	"	+	endTime);
                                }	catch	(Exception	e)	{
                                    throw	new	RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject",	"Выполнен поток №	"	+	numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}

