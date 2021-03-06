package com.android.projectcard;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CardStartActivity extends AppCompatActivity {

    CountDownTimer threeTimer;
    TextView countThree;
    LinearLayout startLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startcard);

        final Button startBtn = (Button) findViewById(R.id.startBtn);
        countThree = (TextView) findViewById(R.id.countThree);
        startLinear = (LinearLayout) findViewById(R.id.startLinear);
        final int millisInFuture = 3100;

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startLinear.setVisibility(View.INVISIBLE);
                countThree.setVisibility(View.VISIBLE);
                threeTimer = new CountDownTimer(millisInFuture,1000){

                    @Override
                    public void onTick(long millisUntilFinished) {

                        String aa = String.valueOf(millisUntilFinished);
                        if      (aa.length() == 4)
                            countThree.setText(aa.substring(0,1));
                        else if (aa.length() == 3){
                            countThree.setText("시작!");
                            countThree.cancelLongPress();
                        }

                    }
                    @Override
                    public void onFinish() {
                        countThree.setText(String.valueOf("시작!"));
                        Intent intent = new Intent(CardStartActivity.this, CardMainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                    }
                };
                threeTimer.start();
            }
        });
    }
}
