package com.android.textattribute;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8 = null;;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("텍스트 속성");

        btn1 = (Button) findViewById(R.id.btn1);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);
        txt7 = (TextView) findViewById(R.id.txt7);
        txt8 = (TextView) findViewById(R.id.txt8);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitle("텍스트 속성 변경 후");
                txt1.setTextSize(20);
                txt2.setTextColor(Color.RED);
                txt5.setSingleLine(true);
                txt6.setTextColor(Color.BLUE);
                txt6.setText("안녕하세요?");
                txt7.setTextSize(40);
                txt8.setText("가나다라마바사아자차카타파하");
            }
        });
    }


}
