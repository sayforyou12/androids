package com.android.audioplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3 = null;
    ListView msv;
    SeekBar sb1;

    private MediaPlayer mMediaPlayer;

    private String path      = "";
    private String audio_url = "";
    private int playPosition = -1; // 실행중인 음악 위치

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        msv = (ListView) findViewById(R.id.msv);
        findViewById(android.R.layout.simple_list_item_single_choice);
        this.path = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.audio_url = this.path +"/Music/Goodbye-Jessica.mp3";

        msv = ;

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(MainActivity.this.audio_url);
                if (file.exists()){
                    playAudio(audio_url);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.this.mMediaPlayer != null){

                    MainActivity.this.playPosition = MainActivity.this.mMediaPlayer.getCurrentPosition();
                    MainActivity.this.mMediaPlayer.pause();
                    Toast.makeText(getApplicationContext(), "음악 중지", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MainActivity.this.mMediaPlayer != null && !MainActivity.this.mMediaPlayer.isPlaying()){
                    mMediaPlayer.seekTo(playPosition);
                    mMediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "음악 재시작", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




    private void playAudio(String url){

        try {
            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setDataSource(url);
            this.mMediaPlayer.prepare();
            this.mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopAudio(){

        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.release();//메모리 해제
        }

    }

}
