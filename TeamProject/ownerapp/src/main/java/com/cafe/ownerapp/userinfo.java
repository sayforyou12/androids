package com.cafe.ownerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class userinfo extends AppCompatActivity implements View.OnClickListener{

    TextView tv_Changepw, tv_Changeinfo, tv_Deleteuser;
    public int confirm = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);

        tv_Changepw = (TextView) findViewById(R.id.tv_changepw);
        tv_Changeinfo = (TextView) findViewById(R.id.tv_change_userinfo);
        tv_Deleteuser = (TextView) findViewById(R.id.tv_delete_userinfo);

        tv_Changepw.setOnClickListener(this);
        tv_Changeinfo.setOnClickListener(this);
        tv_Deleteuser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_changepw:
                confirm = 1;
                Intent intent = new Intent(getApplicationContext(),ConfirmUser.class);
                intent.putExtra("conNum", confirm );
                startActivity(intent);

                break;
            case R.id.tv_change_userinfo:
                confirm = 2;
                Intent intent1 = new Intent(getApplicationContext(),ConfirmUser.class);
                intent1.putExtra("conNum", confirm);
                startActivity(intent1);
                break;
            case R.id.tv_delete_userinfo:
                confirm = 3;
                Intent intent2 = new Intent(getApplicationContext(),ConfirmUser.class);
                intent2.putExtra("conNum", confirm);
                startActivity(intent2);
                break;
        }
    }
}
