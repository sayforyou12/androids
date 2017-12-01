package com.android.mp3_2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewMP3;
    private Button btnPlay, btnStop;
    private TextView tvMP3, tvtime;
    private SeekBar seekMP3;

    private ArrayList<String> mp3List = new ArrayList<>();
    private String selectedMP3;

    private String mp3Path = "/sdcard/";
    private MediaPlayer mPlayer;

    private int positionlistview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMP3 = (TextView) findViewById(R.id.tvMP3);
        tvtime = (TextView) findViewById(R.id.tvTime);
        seekMP3 = (SeekBar) findViewById(R.id.pbMP3);

        File [] listFiles = new File(mp3Path+"/Music").listFiles();
        String fileName, extName;
        for(File file : listFiles){
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);
            if(extName.equals((String) "mp3"));
            mp3List.add(fileName);
        }

        listViewMP3 = (ListView) findViewById(R.id.listViewMP3);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
        listViewMP3.setItemChecked(0, true);

        selectedMP3 = mp3List.get(0);
        mPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(mp3Path+"Music/"+mp3List.get(0)));
        seekMP3.setMax(mPlayer.getDuration());

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPlayer.stop();
                // positionlistview = position;
                // selectedMP3 = mp3List.get(position);
                mPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(mp3Path+"Music/"+mp3List.get(position)));
                seekMP3.setMax(mPlayer.getDuration());
            }
        });

        seekMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b)
                    mPlayer.seekTo(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPlayer.seekTo(seekBar.getProgress());
            }
        });

    }



    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlay:
                start();
                break;
            case R.id.btnStop:
                if (mPlayer != null) {
                    mPlayer.seekTo(0);
                    seekMP3.setProgress(0);
                    mPlayer.stop();
                    // mPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(mp3List.get(0)));
                    tvMP3.setText("실행중인 음악 : "+selectedMP3);
                }
                break;

            case R.id.btnPause:
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                } else {
                    mPlayer.pause();
                }
                break;
        }
    }

    private void start() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mp3Path+"Music/"+selectedMP3);
            mPlayer.prepare();
            mPlayer.start();
            // btnPlay.setClickable(false);
            // btnStop.setClickable(true);
            tvMP3.setText("실행중인 음악 : "+selectedMP3);
            seekMP3.setVisibility(View.VISIBLE);

            Thread();
            task2.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Runnable task2 = new Runnable() {

        public void run() {
            tvtime.postDelayed(task2, 1000);
            String min2 =String.format("%02d",((mPlayer.getCurrentPosition())/1000/60)%60);
            String sec2 =String.format("%02d",((mPlayer.getCurrentPosition())/1000%60));

            String min =String.format("%02d",((mPlayer.getDuration())/1000/60)%60);
            String sec =String.format("%02d",((mPlayer.getDuration())/1000%60));
            tvtime.setText("진행 시간 : "+min2+" : "+ sec2 +" / "+min+" : "+sec);
        }
    };

    private void Thread() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (mPlayer.isPlaying()) {
                    seekMP3.setProgress(mPlayer.getCurrentPosition());
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
