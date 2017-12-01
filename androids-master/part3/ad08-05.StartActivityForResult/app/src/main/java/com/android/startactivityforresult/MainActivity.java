package com.android.startactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sumbtn1;
    EditText edt1, edt2;

    //결과를 받기위한 변수
    int call = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumbtn1 = (Button) findViewById(R.id.sumbtn1);
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);

        sumbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),SubActivity.class);
                intent.putExtra("fir",edt1.getText().toString());
                intent.putExtra("sec",edt2.getText().toString());
                startActivityForResult(intent,0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int i = data.getIntExtra("int2",0);
        Toast.makeText(getApplicationContext(), i + "",Toast.LENGTH_SHORT).show();

    }
}
