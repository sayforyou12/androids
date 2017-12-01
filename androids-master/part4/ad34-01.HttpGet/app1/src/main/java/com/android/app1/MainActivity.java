package com.android.app1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn1 = null;
    EditText edit1 = null;
    TextView tv2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        edit1 = (EditText) findViewById(R.id.edit1);
        tv2 = (TextView) findViewById(R.id.tv2);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new HttpRequestAsyncTask().execute();
            }
        });
    }

    public class HttpRequestAsyncTask extends AsyncTask< Integer, Integer, Integer> {


        ProgressDialog waitDlg = null;

        @Override
        protected Integer doInBackground(Integer... params) {

            // http를 이용하여 데이터 받아오기.
            Integer version = request();
            return version;
        }


        // 작업을 시작하기 전에 필요한 UI를 화면에 보여준다.
        // 시계 표시.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog( MainActivity.this );
            waitDlg.setMessage(" 버전 확인 중 ");
            waitDlg.show();

        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            // ProgressBar 감추기
            if( waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }

            tv2.setText(String.valueOf(integer));
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            // ProgressBar 감추기
            if( waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    private Integer request() {
        Integer result = null;
        InputStream in = null;
        BufferedReader rd = null;
        HttpURLConnection httpConn = null;


        // 서버 주소.
        String weburl = String.format("http://%s/rest/currentversion", edit1.getText().toString() );

        try {
            URL url = new URL(weburl); // URL 객체 생성. 접속 정보 설정.
            httpConn = (HttpURLConnection) url.openConnection(); //Connection 생성

            httpConn.setConnectTimeout( 60000 );// 60초 동안 서버 접속을 기다린다.
            httpConn.setReadTimeout( 60000 );   // 60초 동안 서버 응답을 기다린다.
            httpConn.setRequestMethod("GET");   // HTTP 요청 방식. GET, POST, PUT, DELETE 등등....
            httpConn.setRequestProperty("charset", "utf-8");
            httpConn.connect();                 // 서버 접속 시작.

            //서버의 요청에 대한 응답 코드 받기
            int responsCode = httpConn.getResponseCode();

            // 200=< responsCode && responsCode <= 299 만 정상.
            if ( responsCode < 200 || responsCode >= 300 ){
                //오류
                Log.d("request", httpConn.getResponseMessage() );
                return -1;
            }

            //서버응답을 stream에 담은 후 String으로 변환.
            in = httpConn.getInputStream();
            rd = new BufferedReader( new InputStreamReader(in) );
            StringBuffer   bf = new StringBuffer();

            String line = "";
            for ( ; ( line = rd.readLine() ) != null ; ){
                bf.append(line);
            }


            result = Integer.valueOf( bf.toString() );

        }
        catch (IOException e) {
            e.printStackTrace();
            result = -1;
        }finally {

            try {
                if ( rd != null) rd.close(); //반드시 close() 하시오. 메모리 누수 발생.
                if ( in != null) in.close(); //반드시 close() 하시오. 메모리 누수 발생.
                httpConn.disconnect();//Connect 닫기
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

}
