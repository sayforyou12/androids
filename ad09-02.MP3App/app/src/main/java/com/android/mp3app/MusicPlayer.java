package com.android.mp3app;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MusicPlayer extends AppCompatActivity {

    private Button start, stop, eve, next, ss;
    private MediaPlayer mp;
    private SeekBar seekBar;
    private int sum = 0;
    private TextView textView4, textView5;
    private String sub ;
    private ArrayList<String> songs2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        eve = (Button) findViewById(R.id.button3);
        next = (Button) findViewById(R.id.button4);
        ss = (Button) findViewById(R.id.ss);
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        // textview 파일 리스트
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File mydir = new File (path + "/Music");
        if(!mydir.isDirectory())mydir.mkdir();
        String stringsum = "";
        String [] files = mydir.list();
        for(int i=0; i<=files.length-1; i++) {
            stringsum += files[i]+"\n";
        }

        File [] listfile = new File(path+"/Music").listFiles();
        String filename, extName;
        for(int i=0; i<=listfile.length-1; i++){
            filename = String.valueOf(listfile[i]);
            // extName = filename.substring(filename.length()-3);
            // if(extName.equals((String)"mp3"))
            songs2.add(i, (filename));
        }

        mp = MediaPlayer.create(MusicPlayer.this, Uri.parse(songs2.get(sum)));
        mp.setLooping(true);

        seekBar.setMax(mp.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mp.seekTo(progress);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private Runnable task2 = new Runnable() {

        public void run() {
            textView5.postDelayed(task2, 1000);
            String min2 =String.format("%02d",((mp.getCurrentPosition())/1000/60)%60);
            String sec2 =String.format("%02d",((mp.getCurrentPosition())/1000%60));

            String min =String.format("%02d",((mp.getDuration())/1000/60)%60);
            String sec =String.format("%02d",((mp.getDuration())/1000%60));
            textView5.setText("진행 시간 : "+min2+" : "+ sec2 +" / "+min+" : "+sec);
        }
    };

    public void onClick(View view){
        switch (view.getId()){
            case R.id.stop:
                Stop();
                break;
            case R.id.start:
                Play();
                break;
            case R.id.button3:
                Eve();
                break;
            case R.id.button4:
                Next();
                break;
            case R.id.ss:
                if (!mp.isPlaying()) {
                    mp.start();
                } else {
                    mp.pause();
                }
                break;
        }
    }
    private void Play() {
        if (mp.isPlaying()) { // 재생중이면 정지
            mp.stop();
            try {
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.seekTo(0);
            sub = songs2.get(sum).substring(22);
            textView4.setText("실행중인 음악 : "+sub);
        } else {             // 재생중이 아니면 실행(재생)
            mp.start();
            sub = songs2.get(sum).substring(22);
            textView4.setText("실행중인 음악 : "+sub);
            Thread();
            task2.run();
        }
    }

    private void Thread() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (mp.isPlaying()) {
                    seekBar.setProgress(mp.getCurrentPosition());
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }




    private void Eve()  {
        if (sum > 0) {
            mp.stop();
            sum = sum - 1;
            mp = MediaPlayer.create(MusicPlayer.this, Uri.parse(songs2.get(sum)));
            mp.start();
            sub = songs2.get(sum).substring(22);
            textView4.setText("실행중인 음악 : "+sub);
        } else {
            Toast.makeText(getApplicationContext(), "처음 곡 입니다.", Toast.LENGTH_SHORT).show();
            sub = songs2.get(sum).substring(22);
            textView4.setText("실행중인 음악 : "+sub);
        }
    }

    private void Next() {
        if (sum+1 < songs2.size()) {
            mp.stop();
            sum = sum + 1;
            mp = MediaPlayer.create(MusicPlayer.this, Uri.parse(songs2.get(sum)));
            mp.start();
            sub = songs2.get(sum).substring(22);
            textView4.setText("실행중인 음악 : "+sub);
        } else {
            mp.stop();
            sum = 0;
            mp = MediaPlayer.create(MusicPlayer.this, Uri.parse(songs2.get(sum)));
            mp.start();
            sub = songs2.get(sum).substring(22);
            textView4.setText("실행중인 음악 : "+sub);
        }
    }

    private void Stop() {
        if (mp != null) {
            mp.seekTo(0);
            seekBar.setProgress(0);
            mp.stop();
            mp = MediaPlayer.create(MusicPlayer.this, Uri.parse(songs2.get(sum)));
            sub = songs2.get(sum).substring(22);
            textView4.setText("실행중인 음악 : "+sub);
        }
    }
}

