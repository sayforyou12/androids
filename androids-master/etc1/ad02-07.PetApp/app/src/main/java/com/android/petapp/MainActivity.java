package com.android.petapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2;
    CheckBox cb1;
    LinearLayout lay1;

    RadioButton dogbtn, catbtn, rabbitbtn;

    Button btn1, btn2;

    ImageView anymalimg;

    RadioGroup rg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        cb1 = (CheckBox) findViewById(R.id.cb1);

        lay1 = (LinearLayout) findViewById(R.id.lay1);

        dogbtn = (RadioButton) findViewById(R.id.dogbtn);
        catbtn = (RadioButton) findViewById(R.id.catbtn);
        rabbitbtn = (RadioButton) findViewById(R.id.rabbitbtn);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        anymalimg = (ImageView) findViewById(R.id.anymalimg);

        rg1 = (RadioGroup) findViewById(R.id.rg1);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    tv2.setVisibility(View.VISIBLE);
                    rg1.setVisibility(View.VISIBLE);
                }
                else if (isChecked == false){
                    tv2.setVisibility(View.INVISIBLE);
                    rg1.setVisibility(View.INVISIBLE);
                }
            }
        });

        dogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anymalimg.setImageResource(R.drawable.dog);
            }
        });
        catbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anymalimg.setImageResource(R.drawable.cat);
            }
        });
        rabbitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anymalimg.setImageResource(R.drawable.rabbit);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anymalimg.setVisibility(View.VISIBLE);
                btn2.setVisibility(View.VISIBLE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    cb1.setChecked(false);

            }
        });

    }


}
