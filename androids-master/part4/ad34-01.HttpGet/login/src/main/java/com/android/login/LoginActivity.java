package com.android.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.mylibrary.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

public class LoginActivity extends AppCompatActivity {

    TextView text1;
    EditText idtext2, pwtext2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idtext2 = (EditText) findViewById(R.id.idtext2);
        pwtext2 = (EditText) findViewById(R.id.pwtext2);
        text1 = (TextView) findViewById(R.id.text1);

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public class HttpLogin extends AsyncTask<String, Integer, String>{
        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // 서버요청동안 wating dialog를 보여주도록 함.
            waitDlg = new ProgressDialog( LoginActivity.this );
            waitDlg.setMessage(" 버전 확인 중 ");
            waitDlg.show();
        }


        @Override
        protected String doInBackground(String... params) {

            String id = params[0];
            String pw = params[1];

            String result = test_get_login_success( id, pw);

            return result;
        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //서버요청 후 지움
            if( waitDlg != null){
                waitDlg.dismiss();
                waitDlg = null;
            }

            if (s.equals("1")){
                //seucces
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                // fail
                TextView result = (TextView) findViewById(R.id.text1);
                result.setText("login fail");
            }
        }
    }

    public String test_get_login_success(String id, String pw){
        String weburl = "http://192.168.0.78:8080/rest/login";
        HttpRequest request = null;
        String      response = "";

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("id", id);
            request.addParameter("pw", pw);
            int httpCode = request.post();
            if (httpCode == HttpURLConnection.HTTP_OK){
                response = request.getStringResponse();
            }
            else {
                //error
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.close();
        }

        return response;
    }


}
