package com.easyapps.androidmultithreadingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean stopLoop;
    int counter = 0;
    TextView counterTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTxt = findViewById(R.id.counter);

        Log.i("flow", "onCreate: Thread id of our Main/UI thread : " + Thread.currentThread().getId());
        logSleepThread(250);
        Log.i("flow", "onCreate:  in this test we will try to update the UI(Counter Textview) of main thread" +
                " from the separate thread");

    }

    public void startThread(View view) {
        Log.i("flow", "startThread: start thread Button clicked ");
        stopLoop = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("flow", "run: new thread created,thread id : " + Thread.currentThread().getId());
                logSleepThread(3000);
                if (stopLoop = true)
                    Log.i("flow", "run: loop has started ,it will increment the Counter Variable");
                logSleepThread(3000);
                Log.i("flow", "run: and update the counter TextView with each increment");
                logSleepThread(3000);
                Log.i("flow", "run: we have put this update textView code in try Catch  ");
                logSleepThread(3000);
                Log.i("flow", "run: so we get to se exeption/error ,if any");
                logSleepThread(3000);
                Log.i("flow", "run: if execption/error occurs,the loop will be exited and execption will be logged");
                logSleepThread(3000);
                Log.i("flow", "run: otherwise the loop continues and counter is updated until we stop the separate thread.");
                logSleepThread(3000);

                while (stopLoop) {
                    try {
                        Log.i("flow", "startThread: inside while loop : ");
                        counterTxt.setText("Counter(loops compteted) : " + counter);
                        counter++;
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        Log.i("flow", "run: exeption occurred ");
                        logSleepThread(3000);
                        Log.i("flow", "run: Exeption description : " + e.getMessage());
                        logSleepThread(1500);
                        Log.i("flow", "run: so it proves we can't update UI of main thread from a separate thread.");
                        logSleepThread(1500);
                        Log.i("flow", "run: may be their is a work around ! Lets see ");
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
