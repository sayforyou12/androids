package com.android.petapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cb1;
    RadioGroup rg1;
    TextView tv1, tv2;
    RadioButton rb1, rb2, rb3;
    Button bt2,bt3;
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물 사진 보기");

        tv1 = (TextView) findViewById(R.id.tv1);
        cb1 = (CheckBox) findViewById(R.id.cb1);

        tv2 = (TextView) findViewById(R.id.tv2);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);

        image1 = (ImageView) findViewById(R.id.image1);

        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);



        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(cb1.isChecked() == true){
                    tv2.setVisibility(android.view.View.VISIBLE);
                    rg1.setVisibility(android.view.View.VISIBLE);
                    image1.setVisibility(android.view.View.VISIBLE);
                    bt2.setVisibility(android.view.View.VISIBLE);
                    bt3.setVisibility(android.view.View.VISIBLE);
                }
                else{
                    tv2.setVisibility(android.view.View.INVISIBLE);
                    rg1.setVisibility(android.view.View.INVISIBLE);
                    image1.setVisibility(android.view.View.INVISIBLE);
                    bt2.setVisibility(android.view.View.INVISIBLE);
                    bt3.setVisibility(android.view.View.INVISIBLE);
                }

            }

        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg1.getCheckedRadioButtonId()) {
                    case R.id.rb1:
                        image1.setImageResource(R.drawable.siba);
                        break;
                }
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg1.getCheckedRadioButtonId()) {
                    case R.id.rb2:
                        image1.setImageResource(R.drawable.cat);
                        break;
                }
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg1.getCheckedRadioButtonId()) {
                    case R.id.rb3:
                        image1.setImageResource(R.drawable.rabit);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),
                                "동물을 먼저 선택하세요",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bt2.isClickable() == true){
                    cb1.setChecked(false);

                }

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
