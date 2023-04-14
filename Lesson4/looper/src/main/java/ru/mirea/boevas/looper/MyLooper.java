package ru.mirea.boevas.looper;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.os.Handler;


import java.util.logging.LogRecord;

public	class	MyLooper	extends	Thread{
    public Handler mHandler;
    private	Handler	mainHandler;
    public	MyLooper(Handler	mainThreadHandler)	{
        mainHandler	=mainThreadHandler;
    }
    public	void	run()	{
        Log.d("MyLooper",	"run");
        Looper.prepare();
        mHandler	=	new	Handler(Looper.myLooper())	{


            public	void handleMessage(Message msg)	{

                String	data	=	msg.getData().getString("KEY");
                Log.d("MyLooper	get	message:	",		data);


                Message	message	=	new	Message();
                Bundle	bundle	=	new Bundle();
                bundle.putString("result",	data);
                message.setData(bundle);
                //	Send	the	message	back	to	main	thread	message	queue	use	main	thread	message	Handler.
                mainHandler.sendMessage(message);
            }

        };
        Looper.loop();
    }
}