package com.easyapps.androidmultithreadingdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean stopLoop;
    int counter = 0;
    TextView counterTxt;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTxt = findViewById(R.id.counter);
        handler=new Handler(getApplicationContext().getMainLooper());

        Log.i("flow", "onCreate: Thread id of our Main/UI thread : " + Thread.currentThread().getId());
        logSleepThread(250);
        Log.i("flow", "onCreate: click start thread button to start a new thread");

    }

    public void startThread(View view) {
        Log.i("flow", "startThread: start thread Button clicked ");
        stopLoop = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("flow", "run: new thread created,thread id : " + Thread.currentThread().getId());
                logSleepThread(1000);
                if (stopLoop = true)
                    Log.i("flow", "run: loop has started ,it will increment the Counter Variable");
                logSleepThread(1000);

                while (stopLoop) {
                    try {
                        counter++;
                        Log.i("flow", "run: Counter incremented");
                        Thread.sleep(1000);
                        Log.i("flow", "run: we will put our update CounterTextView inside a runnable object");
                        Log.i("flow", "run: posting a runnable object(task) to UI handler ");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                counterTxt.setText("Counter(loops compteted) : " + counter);
                            }
                        });
                        Log.i("flow", "run: now the app should not crash,because we are not updating UI thread directly from separate thread");
                        Log.i("flow", "----------------------------------------------------------------------------------" +
                                "-----------------------------------------");
                    } catch (Exception e) {
                        Log.i("flow", "run: exeption occurred ");
                        logSleepThread(1000);
                        Log.i("flow", "run: Exeption description : " + e.getMessage());
                        logSleepThread(1000);
                        Log.i("flow", "run: code will hopefully not get to catch block,because we are " +
                                "not updating UI thread directly from separate thread");
                        return;
                    }
                }
                Log.i("flow", "run: thread stopped ");
            }
        }).start();
        //......................................................

        //.................................................
    }

    public void stopThread(View view) {
        Log.i("flow", "stopThread: stop Thread button clicked");
        stopLoop = false;

    }

    public void logSleepThread(long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
