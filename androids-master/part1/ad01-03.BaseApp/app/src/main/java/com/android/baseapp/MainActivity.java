package com.android.baseapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.android.baseapp.R.id.button1;
import static com.android.baseapp.R.id.button2;
import static com.android.baseapp.R.id.button3;
import static com.android.baseapp.R.id.button4;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = null;
        btn1 = (Button) findViewById(button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(mIntent);
                Toast.makeText(getApplicationContext(), "NATE",
                        Toast.LENGTH_LONG).show();
            }
        });
        Button btn2 = null;
        btn2 = (Button) findViewById(button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/911"));
                startActivity(mIntent);
                Toast.makeText(getApplicationContext(), "긴급전화",
                        Toast.LENGTH_LONG).show();
            }
        });
        Button btn3 = null;
        btn3 = (Button) findViewById(button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(mIntent);
                Toast.makeText(getApplicationContext(), "갤러리 열기",
                        Toast.LENGTH_LONG).show();
            }
        });
        Button btn4 = null;
        btn4 = (Button) findViewById(button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View a) {
                finish();
                Toast.makeText(getApplicationContext(), "종료합니다.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}