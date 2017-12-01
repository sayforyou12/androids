package com.android.simplecalculator;

import android.annotation.TargetApi;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2, et3;
    Button btnadd, btnminus, btnmultiply, btndivision;
    String num1, num2;
    Integer result;
    Button[] numbtn = new Button[10];
    Integer[] numbtnid = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,R.id.btn4,
            R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("계산기");

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        btnadd = (Button) findViewById(R.id.btnadd);
        btnminus = (Button) findViewById(R.id.btnminus);
        btnmultiply = (Button) findViewById(R.id.btnmultiply);
        btndivision = (Button) findViewById(R.id.btndivision);

        btnadd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = et1.getText().toString();
                num2 = et2.getText().toString();
                result = Integer.parseInt(num1)+Integer.parseInt(num2);
                et3.setText("계산결과 : "+result.toString());
                return false;
            }
        });
        btnminus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = et1.getText().toString();
                num2 = et2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                et3.setText("계산결과 : "+result.toString());
                return false;
            }
        });
        btnmultiply.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = et1.getText().toString();
                num2 = et2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                et3.setText("계산결과 : "+result.toString());
                return false;
            }
        });
        btndivision.setOnTouchListener(new View.OnTouchListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = et1.getText().toString();
                num2 = et2.getText().toString();
                DecimalFormat form = new DecimalFormat("#.##");
                double result = Integer.parseInt(num1) / Integer.parseInt(num2);
                et3.setText("계산결과 : "+ form.format(result));
                return false;
            }
        });
        for (i = 0; i<numbtnid.length; i++){
        numbtn[i] = (Button) findViewById(numbtnid[i]);
        }

        for(i = 0; i<numbtnid.length; i++){
            final  int index;
            index = i;

            numbtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et1.isFocused()==true){
                        num1 = et1.getText().toString() +
                                numbtn[index].getText().toString();
                        et1.setText(num1);
                    }
                    else if (et2.isFocused()==true){
                        num2 = et2.getText().toString() +
                                numbtn[index].getText().toString();
                        et2.setText(num2);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"먼저 에디트텍스트를 선택하세요.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
