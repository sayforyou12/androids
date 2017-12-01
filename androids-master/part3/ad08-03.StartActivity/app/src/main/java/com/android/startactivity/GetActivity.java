package com.android.startactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class GetActivity extends AppCompatActivity {

    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        text2 = (TextView) findViewById(R.id.gettext);

        Intent intent = getIntent();
        text2.setText(intent.getStringExtra("text"));

    }
}
