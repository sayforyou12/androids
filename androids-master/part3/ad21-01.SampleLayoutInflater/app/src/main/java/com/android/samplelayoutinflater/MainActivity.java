package com.android.samplelayoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    LinearLayout container;
    TextView text1;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);

        text1 = (TextView) findViewById(R.id.text1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container = null;
                inflater = null;

                container = (LinearLayout) findViewById(R.id.container);
                inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.layout_sub1,container,true);
                CheckBox checkBox = (CheckBox) container.findViewById(R.id.checkBox1);
                checkBox.setText("로딩되었습니다.");
            }
        });
    }
}
