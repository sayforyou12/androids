package com.android.scrollview;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddButtonActivity extends AppCompatActivity {


    private LinearLayout layout1;
    private Button addButton1;
    private Button addButton5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_button);

        layout1 = (LinearLayout) findViewById(R.id.lay1);

        addButton1 = (Button) findViewById(R.id.btn1);

        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼이 클릭되면 layout에 버튼을 추가한다.
                //버튼을 만들고 layout에 추가
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                Button btn = new Button(getApplicationContext());
                btn.setText("Push Me");
                btn.setLayoutParams(lp);
                btn.setBackgroundColor(Color.CYAN);
                layout1.addView(btn);

            }
        });
        addButton5 = (Button) findViewById(R.id.btn5);

        addButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼이 클릭되면 layout에 버튼을 추가한다.
                //버튼을 만들고 layout에 추가
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                for(int i = 0; i < 5; i++) {

                    Button btn = new Button(getApplicationContext());
                    btn.setText("Push Me"+i);
                    btn.setLayoutParams(lp);
                    btn.setBackgroundColor(Color.GREEN);
                    layout1.addView(btn);
                }
            }
        });

    }
}
