package com.android.eventlistener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 무명클래스 리스너
        Button btn1 = null;
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"무명 클래스 방식",Toast.LENGTH_SHORT).show();
            }
        });
        //3. 내부 클래스 핸들러 방식
        Button btn3 = null;
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new ButtonClick());

        // 4.implement 핸들러 방식
        Button btn4 = null;
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

    }

    // 2. 인라인 핸들러
    public void btn2Click(View view) {
        Toast.makeText(getApplicationContext(),"인라인 핸들러 방식", Toast.LENGTH_SHORT).show();
    }



    // 3. 내부 클래스 핸들러 방식
    class ButtonClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),"내부 클래스 핸들러 방식", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(),"implement 핸들러 방식", Toast.LENGTH_SHORT).show();
    }
}
