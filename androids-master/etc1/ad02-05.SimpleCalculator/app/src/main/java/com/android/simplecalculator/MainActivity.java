package com.android.simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btn1, btn2, btn3, btn4, btn5;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("초간단 계산기");

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);



        txt1 = (TextView) findViewById(R.id.txt1);



    }
    public void onClick(View v)throws IOException {

        double x = Double.valueOf(edit1.getText().toString()).doubleValue();
        double y = Double.valueOf(edit2.getText().toString()).doubleValue();
        switch (v.getId()){
            case R.id.btn1:
                if (edit1.getText().toString().equals(null) || edit2.getText().toString().equals(null)){
                    Toast.makeText(getApplicationContext(),"값이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String z =String.format("%.1f",x + y);
                    txt1.setText(z);
                }
                break;
            case R.id.btn2:
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String z =String.format("%.1f",x - y);
                    txt1.setText(z);
                }
                break;
            case R.id.btn3:
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String z =String.format("%.1f",x * y);
                    txt1.setText(z);
                }
                break;
            case R.id.btn4:
                if (edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"값이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    String z =String.format("%.1f",x / y);
                    txt1.setText(z);
                }
                break;
        }
    }
}
