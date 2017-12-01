package com.example.administrator.androidtospring;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.texthttp.Model.ModelText;
import com.example.texthttp.http.HttpRequest;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText text, calltext;
    Button sendbtn, getbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text = (EditText) findViewById(R.id.text);
        calltext = (EditText) findViewById(R.id.calltext);
        sendbtn = (Button) findViewById(R.id.sendbtn);
        getbtn = (Button) findViewById(R.id.getbtn);



        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpText().execute(text.getText().toString());
            }
        });

        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public class HttpText extends AsyncTask<Object, Integer, String>{

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            waitDlg = new ProgressDialog(MainActivity.this);
            waitDlg.setMessage("TEXT 전송중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(Object... strings) {

            String text = String.valueOf(strings[0]);

            String result = send(text);
            return result;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (s.equals(text)){
                Toast.makeText(MainActivity.this,"해당 TEXT가 존재합니다.", Toast.LENGTH_SHORT).show();
            }
            else if (!s.equals(text)){
                Toast.makeText(MainActivity.this,"전송완료.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String send(String text){
        String weburl = "http://192.168.0.21:8080/text/writeone2";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;

        try {
            // ModelPerson을 json으로 변환
            ModelText txt = new ModelText(text);
            String data = new Gson().toJson(txt);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                    response = request.getStringResponse(); // 서버값이 리턴된다
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.close();
        }

        return response;
        }

        /*//db불러오기
        public class callhttp extends AsyncTask<String, Integer, ModelText> {

            private ProgressDialog waitDlg = null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // ProgressDialog 보이기
                // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
                waitDlg = new ProgressDialog(MainActivity.this);
                waitDlg.setMessage("TEXT 확인중");
                waitDlg.show();
            }


            @Override
            protected ModelText doInBackground(String... strings) {

                String calltext = strings[0];
                ModelText result =  calls(calltext);
                return result;
            }
        }

        private  ModelText calls(String calltext){

        }*/
}
