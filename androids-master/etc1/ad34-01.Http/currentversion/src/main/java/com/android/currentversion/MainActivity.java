package com.android.currentversion;

import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText edtIpAddr = null;
    private Button btn_httpget = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtIpAddr = (EditText) findViewById(R.id.edt_ipaddr);
        btn_httpget = (Button) findViewById(R.id.btn_httpget);

        btn_httpget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpRequestAsyncTask().execute();
            }
        });
    }

    public class HttpRequestAsyncTask extends AsyncTask< Integer, Integer, Integer>{

        ProgressDialog waitDlg = null;

        /**
         * 작업을 시작하기 전에 필요한 UI를 화면에 보여주도록 한다.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //서버에 요청 동안 Wating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog( MainActivity.this );
            waitDlg.setMessage(" 버전 확인 중");
            waitDlg.show();
        }

        /**
         * 서버에 요청한다.
         */
        @Override
        protected Integer doInBackground(Integer... params) {

            // http 를이용하여 데이터 받아오기.
            Integer version = request();
            return version;
        }

        /**
         * 서버 요청 시에 UI를갱신할필요가있는경우호출
         * doInBackground에서 publicshProgress()를 호출 후 invoked
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 서버 요청 완료 후 화면에 필요한 UI를 보여주도록 한다.
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            //서버 요청 완료 후 Waiting dialog를 제거한다.
            if( waitDlg != null ) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            // 결과를 화면에 출력.
            TextView currentversion = (TextView) findViewById(R.id.currversiontext);
            currentversion.setText( String.valueOf( integer) );
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);

            // ProgressBar 감추기
            if( waitDlg != null ) {
                waitDlg.dismiss();
                waitDlg = null;
            }
        }
    }

    private int request() {
        int result    = -1;
        BufferedReader rd = null;
        InputStream    in = null;
        HttpURLConnection httpConn = null;

        // 서버 주소.
        String weburl = String.format("http://%s/rest/currentversion", edtIpAddr.getText().toString() );

        try {
            URL url = new URL( weburl ); // URL 객체 생성 . 접속 정보 설정.
            httpConn = (HttpURLConnection) url.openConnection();  // Connection 생성.

            httpConn.setConnectTimeout(60000);// 60초 동안 서버 접속을 기다린다.
            httpConn.setReadTimeout(60000);   // 서버 접속 후60초 동안 서버 응답을 기다린다.
            httpConn.setRequestMethod("GET"); // HTTP 요청 방식. GET, POST, PUT, DELETE....            
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // handle url encoded form data
            httpConn.setRequestProperty("charset", "utf-8");   // 데이터 주고 받을 때의 문자 처리 방법 설정.
            httpConn.connect();               // 서버 접속 시작.

            // 서버의 요청에 대한 응답 코드 받기.
            int responseCode = httpConn.getResponseCode();

            // 200=<responseCode && responseCode <=299 만 정상.
            if( responseCode < 200 || responseCode >= 300 ) {
                // 오류
                Log.d("request", httpConn.getResponseMessage() );
                return -1;
            }

            // 서버 응답을 stream 에 담은 후 String 으로 변환.
            in = httpConn.getInputStream();
            rd = new BufferedReader(new InputStreamReader(in) );
            StringBuffer   bf = new StringBuffer();

            String line = "";
            for( ; (line = rd.readLine()) != null ; ){
                bf.append( line );
            }

            result = Integer.valueOf( bf.toString() );
        } catch (IOException e) {
            e.printStackTrace();
            result = -1;
        } finally {
            try {
                if( rd !=null) rd.close(); // 받드시 close() 하시오. 메모리 누수 발생.
                if( in !=null) in.close(); // 받드시 close() 하시오. 메모리 누수 발생.
                httpConn.disconnect();  // Connection 닫기.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
    private int getCurrentVersion() {
        int curversion = 0;
        PackageInfo pkgInfo = null;

        try {
            pkgInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            curversion = pkgInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }

        return curversion;
    }
}
