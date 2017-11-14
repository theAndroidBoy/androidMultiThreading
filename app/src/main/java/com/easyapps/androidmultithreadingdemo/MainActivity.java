package com.easyapps.androidmultithreadingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean stopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("flow", "onCreate: Thread id of our Main/UI thread : "+Thread.currentThread().getId());
        Log.i("flow", "onCreate: we will start a loop on a separate thread");
        Log.i("flow", "onCreate: and see if our Main/UI thread still works");


    }

    public void startThread(View view) {
        Log.i("flow", "startThread: start thread Button clicked ");
        stopLoop=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("flow", "run: new thread created for the loop.");
                Log.i("flow", "run: so we can use UI on main/UI thread without any issue.");
                if(stopLoop=true)
                    Log.i("flow", "run: loop has started ,its logging the thread id on which is executing");
                while (stopLoop)
                {
                    Log.i("flow", "startThread: Thread id in while loop : "+Thread.currentThread().getId()  );
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("flow", "run: thread stopped ");
            }
        }).start();

    }

    public void stopThread(View view) {
        Log.i("flow", "stopThread: stop Thread button clicked");
        stopLoop=false;
        Log.i("flow", "stopThread: see we can still click the stop button  ");
        Log.i("flow", "stopThread: because that loop is in a separate thread ");
        Log.i("flow", "stopThread: and this click button task was executed on main/UI thread");
    }
}
