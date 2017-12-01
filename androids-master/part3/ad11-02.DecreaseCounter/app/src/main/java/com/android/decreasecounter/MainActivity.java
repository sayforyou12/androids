package com.android.decreasecounter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int mCount = 5;
    TextView txt1 = null;
    Handler mHandler = null;
    Thread countThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        txt1 = (TextView) findViewById(R.id.txt1);

        if (mCount == 0 ){
            countThread.interrupt();
        }
        countThread = new Thread(){
            @Override
            public void run() {
                super.run();

                for (int i = 5; i >= 0; i--){
                    mCount--;

                    Log.i("superdroid", "Current count : " + mCount);
                    try {
                        Thread.sleep(1000);
                        Runnable message = new Runnable() {
                            @Override
                            public void run() {

                                txt1.setText("Count : " + mCount);
                            }
                        };

                        txt1.post(message);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (i == 0){
                        interrupt();
                    }

                    /*countThread.interrupt();*/


                }

            }
        };countThread.start();
    }
}
