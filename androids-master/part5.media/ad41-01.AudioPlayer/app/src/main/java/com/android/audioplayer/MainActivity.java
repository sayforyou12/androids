package com.android.audioplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    private int playPostion = -1; // 실행중인 음악의 위치

    // "/sdcard/Music/Goodbye-Jessica.mp3"
    private String path      = "";
    private String AUDIO_URL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = (Button) findViewById(R.id.playBtn);
        Button pauseBtn = (Button) findViewById(R.id.pauseBtn);
        Button restartBtn = (Button) findViewById(R.id.restartBtn);

        this.path = Environment.getExternalStorageDirectory().getAbsolutePath(); // == "/sdcard"
        this.AUDIO_URL = this.path +"/Music/Goodbye-Jessica.mp3";

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File( MainActivity.this.AUDIO_URL );
                if( file.exists() ) {
                    playAudio( AUDIO_URL );
                    Toast.makeText(getApplicationContext(), "음악 시작", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( MainActivity.this.mediaPlayer != null ) {

                    MainActivity.this.playPostion = MainActivity.this.mediaPlayer.getCurrentPosition();
                    MainActivity.this.mediaPlayer.pause();
                    Toast.makeText(getApplicationContext(), "음악 중지", Toast.LENGTH_SHORT).show();
                }
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( MainActivity.this.mediaPlayer != null && ! MainActivity.this.mediaPlayer.isPlaying()){

                    mediaPlayer.seekTo( playPostion );
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "음악 재시작", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void playAudio( String url) {
        killMediaPlayer();

        try {
            this.mediaPlayer = new MediaPlayer();
            this.mediaPlayer.setDataSource(url);
            this.mediaPlayer.prepare();
            this.mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer( ) {
        if( this.mediaPlayer != null){
            this.mediaPlayer.release(); // 메모리 해제
        }

    }
}
