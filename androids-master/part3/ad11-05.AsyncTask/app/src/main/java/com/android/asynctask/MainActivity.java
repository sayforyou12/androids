package com.android.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
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

        new CounterTask().execute(20);

    }
    class CounterTask extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... params) {

            int count = 0;
            for (int i = 0; i<=params[0]; i++){
                SystemClock.sleep(1000);//1초 멈춤
                publishProgress(count++, i);//handler.post()의 역활
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mCounttextView.setText( "count : "+values[0]+" , i : "+values[1]);
        }
    }
}
