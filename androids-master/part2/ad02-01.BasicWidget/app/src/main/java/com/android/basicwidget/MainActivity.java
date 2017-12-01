package com.android.basicwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //선언, 찾기, 설정
        Button btn1 = null;
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {

                //Toast 메세지 출력
                Toast.makeText(getApplicationContext(),"btn1", Toast.LENGTH_SHORT).show();
                // 새창 띄우기 : RadioActivity
                Intent intent = new Intent( getApplicationContext(), RadioActivity.class);
                startActivity(intent);
            }
        });
        Button btn2 = null;
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View b) {
                Toast.makeText(getApplicationContext(),"btn2", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), WidhtButtonAtivity.class);
                startActivity(intent2);
            }
        });
        Button btn3 = null;
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View c) {
                Toast.makeText(getApplicationContext(),"btn3", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), HeightButtonAtivity.class);
                startActivity(intent3);
            }
        });
        Button btn4 = null;
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View d) {
                Toast.makeText(getApplicationContext(),"btn4", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), ColorButtonAtivity.class);
                startActivity(intent3);
            }
        });
    }
}
