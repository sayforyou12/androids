package com.android.etc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnA, btnS,btnM,btnD;
    TextView text1;
    String num1, num2;
    Integer result;
    Button[] numButtons = new Button[10];
    Integer[]numBtnIDs = {R.id.bt0, R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7
    ,R.id.bt8 ,R.id.bt9};
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("계산기");

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        btnA = (Button) findViewById(R.id.btnA);
        btnS = (Button) findViewById(R.id.btnS);
        btnM = (Button) findViewById(R.id.btnM);
        btnD = (Button) findViewById(R.id.btnD);
        text1 = (TextView) findViewById(R.id.text1);

        btnA.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1)+Integer.parseInt(num2);
                text1.setText("계산결과 : " + result.toString());
                return false;
            }
        });
        btnS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1)-Integer.parseInt(num2);
                text1.setText("계산결과 : " + result.toString());
                return false;
            }
        });
        btnM.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1)*Integer.parseInt(num2);
                text1.setText("계산결과 : " + result.toString());
                return false;
            }
        });
        btnD.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1)/Integer.parseInt(num2);
                text1.setText("계산결과 : " + result.toString());
                return false;
            }
        });
        for(i = 0; i < numBtnIDs.length; i++){
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for(i = 0; i < numBtnIDs.length; i++){
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edit1.isFocused()== true){
                        num1 = edit1.getText().toString()
                                + numButtons[index].getText().toString();
                        edit1.setText(num1);
                    }
                    else if(edit2.isFocused()==true){
                        num2 = edit2.getText().toString()
                                +numButtons[index].getText().toString();
                        edit2.setText(num2);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "먼저 에디트텍스트를 선택하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
