package seveno.android.miniseconds;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText idet, pwet, nicket;
    Button signupbtn, canclebtn, idcheck, nickcheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        idet = (EditText) findViewById(R.id.idet);
        pwet = (EditText) findViewById(R.id.pwet);
        nicket = (EditText) findViewById(R.id.nicket);
        signupbtn = (Button) findViewById(R.id.signupbtn);
        canclebtn = (Button) findViewById(R.id.canclebtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dailog창 생성
                AlertDialog.Builder dlg = new AlertDialog.Builder(SignUp.this);
                dlg.setTitle("회원가입 화면으로 갑니다.");
                //확인 클릭시 회원가입으로 넘어감
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Logins.class);
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

    public class signup extends AsyncTask<Object, Integer, String>{

        private ProgressDialog waitDlg = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(SignUp.this);
            waitDlg.setMessage("회원가입 요청중");
            waitDlg.show();
        }

        @Override
        protected String doInBackground(Object... params) {

            String id   = String.valueOf(params[0]);
            String pw   = String.valueOf(params[1]);
            String nick = String.valueOf(params[2]);


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    public String HttpSignup(String id, String pw, String nick){
        String weburl = "";

        return null;
    }

}
