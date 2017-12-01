package com.android.mainthread;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int mCount = 10;
    TextView mCounttextView = null;
    Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        mCounttextView = (TextView) findViewById(R.id.mCounttextView);
        Button btn1 = (Button) findViewById(R.id.btn1);

        final Thread countThread = new Thread() {
            @Override
            public void run() {
                // 1. 10초동안 1씩 카운트
                for (int i = 10; i == 0; i--) {
                    mCount = i;

                    // 2.현재 카운트된 값을 로그로 출력
                    Log.i("superdroid", "Current Count : " + mCount);
                    try {
                        Thread.sleep(1000);
                        Runnable message = new Runnable() {
                            @Override
                            public void run() {

                                //3 텍스트뷰에 현재까지 카운트된 수를 출력
                                mCounttextView.setText("Count : " + mCount);
                            }
                            public  void stopThread(){

                            }
                        };

                        // 메인 스레드의 MessageQueue에 던진다.
                        mCounttextView.post(message);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (i == 0){

                    }
                }
            }
        };
        countThread.start();

        /*btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounttextView.setText("Count : " + mCount--);
                if (mCount < 1){


                }

            }
        });*/
    }
}
