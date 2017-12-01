package seveno.android.miniseconds;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Logins extends AppCompatActivity {
    EditText InputId, InputPw;
    Button loginbtn, signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logins);

        InputId = (EditText) findViewById(R.id.InputId);
        InputPw = (EditText) findViewById(R.id.InputPw);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        signupbtn = (Button) findViewById(R.id.signupbtn);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dailog창 생성
                AlertDialog.Builder dlg = new AlertDialog.Builder(Logins.this);
                dlg.setTitle("회원가입 화면으로 갑니다.");
                //확인 클릭시 회원가입으로 넘어감
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), SignUp.class);
                        startActivity(intent);
                    }
                });

                /*dlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });*/
                //취소 클릭시 cancle
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });

    }

    public class login extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


    }
}
