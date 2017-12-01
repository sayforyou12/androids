package com.cafe.adminapp.userinfo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.cafe.adminapp.MainActivity;
import com.cafe.adminapp.R;
import com.cafe.common.Http.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;


public class DeleteUser extends AppCompatActivity {

    private CheckBox ck_deluser;
    private Button btn_delete_ok;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_user);

        ck_deluser = (CheckBox) findViewById(R.id.ck_del_user);
        btn_delete_ok = (Button) findViewById(R.id.btn_delete_ok);

        // 값 받아오기
        Intent intent = getIntent();
        email = intent.getExtras().getString("email");

        btn_delete_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ck_deluser.isChecked() == true){
                    Toast toast = Toast.makeText(getApplicationContext(),"탈퇴 완료",Toast.LENGTH_SHORT);
                    toast.show();
                    new HttpDeleteUser().execute(email);
                }
                else {
                    Toast toast1 = Toast.makeText(getApplicationContext(),"체크박스를 체크해 주세요",Toast.LENGTH_SHORT);
                    toast1.show();
                }

            }
        });

    }

    public class HttpDeleteUser extends AsyncTask<String, Integer, String> {// 첫 param 인자 = ID, PW 가운데 인자는 현재 진행률 = Integer 결과 Id,PW String

        ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //ProgressDialog 보이기
            //서버에 요청 동안 Waiting dialog를 보여주도록 한다
            waitDlg = new ProgressDialog(DeleteUser.this);
            waitDlg.setMessage("기다리삼");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String email = params[0];   //  1번쨰 방 = email

            deleteuser(email);

            //String result = changeuserinfo();
            return email;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //ProgressDialog 감추기 : 서버 요청 완료 후 Waiting dialog 감추기
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            // 받은 결과 출력
                //success
                Intent intent = new Intent(DeleteUser.this,MainActivity.class);
                startActivity(intent);
                finish();
        }
    }

    public String deleteuser(String email){
        String weburl = "http://dbqudtjd1122.cafe24.com/user/deleteUser";

        HttpRequest request = null;
        String response = null;

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("email", email);
            int httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK){
                response = request.getStringResponse();

            }else{
                //error
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return response;
    }

}
