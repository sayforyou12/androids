package com.android.compoundwidget;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg1, rg2;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    LinearLayoutCompat lay1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);



        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (rg1 == group){
                    if (checkedId == R.id.rb1){
                        rg1.setOrientation(LinearLayout.HORIZONTAL);
                    }
                    else if (checkedId == R.id.rb2){
                        rg1.setOrientation(LinearLayout.VERTICAL);
                    }
                }
            }
        });

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (rg2 == group){
                    if (checkedId ==R.id.rb3){

                        rg2.setGravity(Gravity.LEFT);
                    }
                    else if (checkedId == R.id.rb4){
                        rg2.setGravity(Gravity.CENTER_HORIZONTAL);
                    }
                    else if (checkedId == R.id.rb5){
                        rg2.setGravity(Gravity.RIGHT);
                    }
                }
            }
        });
    }
}
