package com.android.barandthread;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar sb1, sb2, sb3;
    TextView txtview1, txtview2, txtview3;
    Button plusbtn, minusbtn, startbtn;
    ProgressBar pb1;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = (ProgressBar) findViewById(R.id.pb1);

        plusbtn = (Button) findViewById(R.id.plusbtn);
        minusbtn = (Button) findViewById(R.id.minusbtn);

        txtview1 = (TextView) findViewById(R.id.txtview1);
        sb1 = (SeekBar) findViewById(R.id.sb1);

        txtview2 = (TextView) findViewById(R.id.txtview2);
        sb2 = (SeekBar) findViewById(R.id.sb2);

        txtview3 = (TextView) findViewById(R.id.txtview3);
        sb3 = (SeekBar) findViewById(R.id.sb3);

        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb1.incrementProgressBy(10);
            }
        });

        minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb1.incrementProgressBy(-10);
            }
        });

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtview1.setText( "진행률  : " + progress+ "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startbtn = (Button) findViewById(R.id.startbtn);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler = new Handler();
                Thread th1 = new Thread(){

                    @Override
                    public void run(){

                        for (int i = sb2.getProgress(); i<= sb2.getMax(); i = i + 2){
                            handler.post(new Runnable() {

                                @Override
                                public void run () {
                                    sb2.setProgress(sb2.getProgress()+2);
                                    txtview2.setText("1번 진행률 : " + sb2.getProgress()+"%");


                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                };
                th1.start();

                Thread th2 = new Thread(){
                    @Override
                    public void run(){
                        for (int i = sb3.getProgress(); i<= sb3.getMax(); i = i + 1){
                            handler.post(new Runnable() {
                                @Override
                                public void run () {
                                    sb3.setProgress(sb3.getProgress()+1);
                                    txtview3.setText("2번 진행률 : " + sb3.getProgress()+"%");

                                }
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                };
                th2.start();

            }
        });

    }
}
