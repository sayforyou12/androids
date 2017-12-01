package com.android.startactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {


    Button sumbnt2;
    int fir, sec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        fir = Integer.parseInt(intent.getExtras().getString("fir").toString());
        sec = Integer.parseInt(intent.getExtras().getString("sec").toString());
        final int k = fir + sec;

        sumbnt2 = (Button) findViewById(R.id.sumbtn2);
        sumbnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent int1 = new Intent(SubActivity.this,MainActivity.class);
                int1.putExtra("int2",k);
                setResult(Activity.RESULT_OK, int1);
                finish();
            }
        });


    }
}
