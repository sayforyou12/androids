package com.cafe.adminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelUser;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtemail, edtpw, edtphone, edtaddr, nickname;
    private CheckBox checkemail, checkall, checkBox1, checkBox2;
    private RadioButton rtn1, rtn2;
    private RadioGroup rtng;
    private Button btn_final;
    private String sex;
    private Integer emailcheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        edtemail = (EditText) findViewById(R.id.edt_input_email);
        edtpw = (EditText) findViewById(R.id.edt_input_pw);
        edtphone = (EditText) findViewById(R.id.edt_input_num);
        edtaddr = (EditText) findViewById(R.id.edt_input_addr);
        nickname = (EditText) findViewById(R.id.edt_input_nickname);

        checkemail = (CheckBox) findViewById(R.id.checkBox);
        checkall = (CheckBox) findViewById(R.id.checkALL);
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        rtn1 = (RadioButton) findViewById(R.id.radioButton1);
        rtn2 = (RadioButton) findViewById(R.id.radioButton2);
        rtng = (RadioGroup) findViewById(R.id.radioGroup);

        btn_final = (Button) findViewById(R.id.btn_final);

        checkall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    checkBox1.setChecked(true);
                    checkBox2.setChecked(true);
                }
                else {
                    checkBox1.setChecked(false);
                    checkBox2.setChecked(false);
                }
            }
        });

        btn_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((checkBox1.isChecked() == false || checkBox2.isChecked() == false )|| (rtn1.isChecked() == rtn2.isChecked())){
                    return;
                }
                else if(edtemail.getText().toString().equals("") || edtpw.getText().toString().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"ID와 비밀번호를 입력해 주세요",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    if(rtn1.isChecked() == true){sex = "남자";} else {sex = "여자";}
                    if(checkemail.isChecked() == true) {
                        emailcheck = 1;
                    } else {
                        emailcheck = 0;
                    }
                    new HttpLogin().execute(edtemail.getText().toString(), edtpw.getText().toString(), edtphone.getText().toString(), edtaddr.getText().toString(), sex.toString(), emailcheck,  nickname.getText().toString());
                }
            }
        });


    }
    public class HttpLogin extends AsyncTask<Object, Integer, String> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(SignUpActivity.this);
            waitDlg.setMessage(" 버전 확인 중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(Object... strings) {

            String email = String.valueOf(strings[0]);
            String pw = String.valueOf(strings[1]);
            String phone = String.valueOf(strings[2]);
            String addr = String.valueOf(strings[3]);
            String sex = String.valueOf( strings[4]);
            Integer checkemail = (Integer) strings[5];
            String nickname = String.valueOf(strings[6]);


            String result = insert(email, pw, phone, addr, sex, checkemail, nickname );

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
            if (s.equals("1")) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }


    }

    public String insert(String email, String pw, String phone, String addr, String sex, Integer checkemail, String nickname ){
        String weburl = "http://dbqudtjd1122.cafe24.com/team/insertuser";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {
            // ModelPerson을 json으로 변환
            ModelUser obj = new ModelUser(email, pw, phone, addr, sex, checkemail, nickname) ;

            String data = new Gson().toJson(obj);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getStringResponse(); // 서버값이 리턴된다
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return response;
    }
}
