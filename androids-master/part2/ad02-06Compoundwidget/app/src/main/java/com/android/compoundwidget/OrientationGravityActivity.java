package com.android.compoundwidget;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OrientationGravityActivity extends AppCompatActivity {

    //위젯 선언
    private RadioGroup rg1 = null;
    private RadioButton rbt1 = null;
    private RadioButton rbt2 = null;
    private RadioGroup rg2 = null;
    private RadioButton rbt3 = null;
    private RadioButton rbt4 = null;
    private RadioButton rbt5 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_gravity);

        //위젯 찾기
        rg1 = (RadioGroup) findViewById(R.id.rg1);

        // 핸들러 설정
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {


            }
        });

    }
}
