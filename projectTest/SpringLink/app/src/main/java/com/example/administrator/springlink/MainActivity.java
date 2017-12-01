package com.example.administrator.springlink;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvMemo;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkTask networkTask = new NetworkTask();
        networkTask.execute("");
    }

    public class NetworkTask extends AsyncTask{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Object[] objects) {
            //HTTP요청 준비작업
            HttpClient.Builder http = new HttpClient.Builder("POST", "");

            //HTTP요청전송
            HttpClient post = http.create();
            return null;
        }
    }
}
