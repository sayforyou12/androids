package com.example.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkTask networkTask = new NetworkTask();
        networkTask.execute("");
    }

    public class NetworkTask extends AsyncTask<String,String,String>{

        /**
         * doInBackground 실행되기 이전에 동작한다.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 본 작업을 쓰레드로 처리해준다.
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {

            // HTTP 요청 준비 작업
            HttpClient.Builder http = new HttpClient.Builder("POST",
                    "http://192.168.0.21:8080");

            // HTTP 요청 전송
            HttpClient post = http.create();
            post.request();

            // 응답 상태코드 가져오기
            int statusCode = post.getHttpStatusCode();

            // 응답 본문 가져오기
            String body = post.getBody();

            return body;
        }

        /**
         * doInBackground 종료되면 동작한다.
         * @param s : doInBackground가 리턴한 값이 들어온다.
         */
        @Override
        protected void onPostExecute(String s) {
            Log.d("HTTP_RESULT", s);
        }
    }
}
