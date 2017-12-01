package com.android.basicwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class RadioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        //선언, 찾기, 설정
        RadioButton radio1 = null;
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {

                //Toast 메세지 출력
                 Toast.makeText(getApplicationContext(),"여자", Toast.LENGTH_SHORT).show();
                // 새창 띄우기 : RadioActivity
            }
        });
        RadioButton radio2 = null;
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View r) {

                //Toast 메세지 출력
                Toast.makeText(getApplicationContext(),"남자", Toast.LENGTH_SHORT).show();
                // 새창 띄우기 : RadioActivity
            }
        });
    }
}