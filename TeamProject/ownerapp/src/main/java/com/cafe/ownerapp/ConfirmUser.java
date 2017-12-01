package com.cafe.ownerapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ConfirmUser extends AppCompatActivity {

    private int conNum;
    Button btn_Confirm;
    private EditText email, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_user);

        Intent intent = getIntent();
        conNum = intent.getExtras().getInt("conNum");

        email = (EditText) findViewById(R.id.edt_confirm_user_email);
        passwd = (EditText) findViewById(R.id.edt_confirm_user_pw);

        btn_Confirm = (Button) findViewById(R.id.btn_confirm_ok);

        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conNum == 1){
                    new HttpChangePw().execute(email.getText().toString(), passwd.getText().toString());
                }
                else if (conNum == 2) {
                    new HttpChangeUserInfo().execute(email.getText().toString(), passwd.getText().toString());
                }
                else if (conNum == 3) {
                    new HttpDeleteUser().execute(email.getText().toString(), passwd.getText().toString());
                }
            }
        });


    }
    // Http 로그인 확인
    public class HttpDeleteUser extends AsyncTask<String, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(ConfirmUser.this);
            waitDlg.setMessage(" ID / PW 확인 중");
            waitDlg.show();
        }

        @Override
        protected ModelUser doInBackground(String... params) {

            String id = params[0];
            String pw = params[1];

            ModelUser result = login(id, pw);

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ModelUser modelUser) {
            super.onPostExecute(modelUser);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

                Intent intent = new Intent(getApplicationContext(), DeleteUser.class);
                intent.putExtra("email", modelUser.getEmail().toString());
                startActivity(intent);


        }
    }
    public class HttpChangePw extends AsyncTask<String, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(ConfirmUser.this);
            waitDlg.setMessage(" ID / PW 확인 중");
            waitDlg.show();
        }

        @Override
        protected ModelUser doInBackground(String... params) {

            String id = params[0];
            String pw = params[1];

            ModelUser result = login(id, pw);

            return result;
        }

        //
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ModelUser modelUser) {
            super.onPostExecute(modelUser);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            Intent intent = new Intent(getApplicationContext(), ChangePw.class);
            intent.putExtra("email", modelUser.getEmail().toString());
            intent.putExtra("passwd", modelUser.getPasswd().toString());
            startActivity(intent);

        }
    }

    public class HttpChangeUserInfo extends AsyncTask<String, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(ConfirmUser.this);
            waitDlg.setMessage(" ID / PW 확인 중");
            waitDlg.show();
        }

        @Override
        protected ModelUser doInBackground(String... params) {

            String id = params[0];
            String pw = params[1];

            ModelUser result = login(id, pw);

            return result;
        }

        //
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(ModelUser modelUser) {
            super.onPostExecute(modelUser);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            Intent intent = new Intent(getApplicationContext(), ChangeUserInfo.class);
            intent.putExtra("email", modelUser.getEmail().toString());
            startActivity(intent);


        }
    }


    public ModelUser login(String id, String passwd) {
        String weburl = "http://192.168.0.54:8080/team/login";

        HttpRequest request = null;
        JSONObject response = null;
        ModelUser user = null;


        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("email", id);
            request.addParameter("passwd", passwd);

            int httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) {

                response = request.getJSONObjectResponse();
            } else {
                // error
            }
            String jsonInString = response.toString();
            user = new Gson().fromJson(jsonInString, ModelUser.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return user;

    }
}