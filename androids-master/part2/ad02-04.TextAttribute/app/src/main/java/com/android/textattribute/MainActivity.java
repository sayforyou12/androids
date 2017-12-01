package com.android.textattribute;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView text1, text2, text3, text4, text5, text6, text7, text8 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
        text6 = (TextView) findViewById(R.id.textView6);
        text7 = (TextView) findViewById(R.id.textView7);
        text8 = (TextView) findViewById(R.id.textView8);

        Button btn1 = null;
        btn1 = (Button) findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTitle("텍스트 속성 변경 후");
                text1.setTextSize(20);
                text2.setTextColor(Color.RED);
                text3.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
                text4.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                text6.setTextColor(Color.BLUE);
                text6.setText("안녕하세요");
                text7.setTextSize(30);
                text8.setText("가나다라마바사아자차카타파하");
                Toast.makeText(getApplicationContext(),"변경",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
