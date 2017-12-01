package com.cafe.adminapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cafe.adminapp.R;
import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelUser;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

public class IDPW_Search extends AppCompatActivity {

    EditText edttext_phone, edttext_email;
    Button findemailbtn, findpwdbtn;
    TextView viewemail, viewepwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idpw_search);

        edttext_email = (EditText) findViewById(R.id.edttext_email);
        edttext_phone = (EditText) findViewById(R.id.edttext_phone);
        findemailbtn = (Button) findViewById(R.id.findemailbtn);
        findpwdbtn = (Button) findViewById(R.id.findpwdbtn);
        viewemail = (TextView) findViewById(R.id.viewemail);
        viewepwd = (TextView) findViewById(R.id.viewepwd);
        findemailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edttext_phone.getText().toString();

                new Httpfindemail().execute(phone);

            }
        });

        findpwdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edttext_email.getText().toString();

                new Httpfindepw().execute(email);
            }
        });
    }

    public class Httpfindemail extends AsyncTask<String, Integer, ModelUser> {

        ProgressDialog waitDlg = null;

        @Override
        protected ModelUser doInBackground(String... params) {

            String phone = params[0];

            ModelUser result = getemail(phone);

            return result;
        }


        @Override
        protected void onPostExecute(ModelUser modeluser) {
            super.onPostExecute(modeluser);

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            if (modeluser == null) {
                Toast.makeText(getApplicationContext(), "입력하신 전화번호가 없습니다.", Toast.LENGTH_SHORT).show();
            } else {
                String str = modeluser.getEmail().toString();

                int idx = str.indexOf("@");
                String front = str.substring(0, idx);
                String reartext = str.substring(idx);

                StringBuffer sb = new StringBuffer(front);
                String fronttext = sb.replace(front.length() - 3, front.length(), "***").toString();

                viewemail.setText("귀하의 아이디는 " + fronttext + reartext + " 입니다.");

                edttext_phone.setText("");

            }
        }
    }


    public ModelUser getemail(String phone) {
        String weburl = "http://dbqudtjd1122.cafe24.com/team/selectid";

        HttpRequest request = null;
        String response = null;
        ModelUser userphone = null;

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("userphone", phone);
            int httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) {
                response = request.getStringResponse();

            } else {
                //error
            }
            String jsonInString = response.toString();
            userphone = new Gson().fromJson(jsonInString, ModelUser.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return userphone;
        }

    }

    public class Httpfindepw extends AsyncTask<String, Integer, ModelUser> {

        ProgressDialog waitDlg = null;

        @Override
        protected ModelUser doInBackground(String... params) {

            String useremail = params[0];

            ModelUser result = getpw(useremail);

            return result;
        }

        @Override
        protected void onPostExecute(ModelUser modeluser) {
            super.onPostExecute(modeluser);

            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }

            if (modeluser == null) {
                Toast.makeText(getApplicationContext(), "입력하신 이메일이 없습니다.", Toast.LENGTH_SHORT).show();

            } else {

                String str = modeluser.getPasswd().toString();
                StringBuffer sb = new StringBuffer(str);
                StringBuffer pw = sb.replace(str.length()-3, str.length(),"***");

                viewepwd.setText("귀하의 비밀번호는 " + pw + " 입니다.");
                edttext_email.setText("");
            }
        }
    }

    public ModelUser getpw(String useremail) {
        String weburl = "http://dbqudtjd1122.cafe24.com/team/selectpwd";

        HttpRequest request = null;
        String response = null;
        ModelUser email = null;

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("email", useremail);
            int httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) {
                response = request.getStringResponse();

            } else {
                //error
            }
            String jsonInString = response.toString();
            email = new Gson().fromJson(jsonInString, ModelUser.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
            return email;
        }
    }
}
