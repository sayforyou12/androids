package com.cafe.adminapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cafe.common.CommonActvity;
import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class LoginActivity extends CommonActvity {

    private EditText editID, editPW;
    private Button btnLogin, Signup,findidpw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        // 좌측 메뉴 로그인
        editID = (EditText) findViewById(R.id.editID);
        editPW = (EditText) findViewById(R.id.editPW);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        Signup = (Button) findViewById(R.id.button);
        findidpw = (Button) findViewById(R.id.findidpw);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AysncTask 호출
                String id = editID.getText().toString();
                String pw = editPW.getText().toString();
                new HttpLogin().execute(id, pw);
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent2);
            }
        });
        findidpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), IDPW_Search.class);
                startActivity(intent3);
            }
        });
    }

    // Http 로그인 확인
    public class HttpLogin extends AsyncTask<String, Integer, ModelUser> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(LoginActivity.this);
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
            if(modelUser == null){
                Toast.makeText(LoginActivity.this, "ID 또는 PW 가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            } else {
                String nickname = modelUser.getUsernickname().toString();
                String level = String.valueOf(modelUser.getUserlevel().toString());
                String email = modelUser.getEmail().toString();
                Integer userno = modelUser.getUserno();

                SharedPreferences.Editor prefEditor = pref.edit();
                prefEditor.putString("nickname_Set", nickname);
                prefEditor.putString("level_Set", level);
                prefEditor.putString("email",email);
                prefEditor.putInt("userno_Set", userno);
                prefEditor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nickname_Set", nickname);
                intent.putExtra("level_Set", level);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public ModelUser login(String id, String pw) {
        String weburl = "http://dbqudtjd1122.cafe24.com/team/login";

        HttpRequest request = null;
        JSONObject response = null;
        ModelUser user = null;

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("email", id);
            request.addParameter("passwd", pw);

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
            return user;
        }
    }
}