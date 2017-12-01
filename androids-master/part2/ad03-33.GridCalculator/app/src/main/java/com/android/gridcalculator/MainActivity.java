package com.android.gridcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = { R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9 };

    Button btnadd = null;
    Button btnsub = null;
    Button btnmul = null;
    Button btndiv = null;
    Button btnrem = null;
    Button btnreset = null;

    EditText edit1 = null;
    EditText edit2 = null;
    TextView result= null;

    String num1 = "";
    String num2 = "";

    Integer i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = (Button) findViewById(R.id.button10);
        btnsub = (Button) findViewById(R.id.button11);
        btnmul = (Button) findViewById(R.id.button12);
        btndiv = (Button) findViewById(R.id.button13);
        btnrem = (Button) findViewById(R.id.button14);
        btnreset = (Button) findViewById(R.id.button15);

        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        result = (TextView) findViewById(R.id.editText3);

        edit1.setInputType(0);
        edit2.setInputType(0);


        for (i = 0 ; i < numBtnIDs.length; i++){
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }

        for (i = 0 ; i < numBtnIDs.length; i++){
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edit1.isFocused() == true){
                        num1 = edit1.getText().toString() + numButtons[index].getText().toString();
                        edit1.setText(num1);
                    }
                    else if (edit2.isFocused() == true){
                        num2 = edit2.getText().toString() + numButtons[index].getText().toString();
                        edit2.setText(num2);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "먼저 텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "숫자를 둘 다 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    i =  Integer.parseInt(num1) + Integer.parseInt(num2);
                    result.setText(i.toString());
                    result.setText("계산 결과:  " + i.toString());
                }
            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "숫자를 둘 다 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    i =  Integer.parseInt(num1) - Integer.parseInt(num2);
                    result.setText(i.toString());
                    result.setText("계산 결과:  " + i.toString());
                }
            }
        });

        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "숫자를 둘 다 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    i =  Integer.parseInt(num1) * Integer.parseInt(num2);
                    result.setText(i.toString());
                    result.setText("계산 결과:  " + i.toString());
                }
            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "숫자를 둘 다 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else if (edit2.getText().toString().equals("0") ){
                    Toast.makeText(getApplicationContext(), "0이 아닌 수로 나눠주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    i =  Integer.parseInt(num1) / Integer.parseInt(num2);
                    result.setText("계산 결과:  " + i.toString());
                }
            }
        });

        btnrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "숫자를 둘 다 입력해 주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    i =  Integer.parseInt(num1) % Integer.parseInt(num2);
                    result.setText(i.toString());
                    result.setText("계산 결과:  " + i.toString());
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit1.setText(null);
                edit2.setText(null);
            }
        });



    }
}
