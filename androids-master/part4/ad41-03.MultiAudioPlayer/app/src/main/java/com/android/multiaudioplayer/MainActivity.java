package com.android.multiaudioplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewMP3;
    Button btnPlay, btnPause, btnStop;

    TextView tvMP3, tvTime;
    SeekBar pbMP3;

    List<String> mp3List;
    ArrayAdapter<String> adapter;//mp3List와 ListView를 연결하는 adapter
    String selectedMP3;
    String mp3Path;
    Integer positionListView;

    MediaPlayer mPlayer;
    boolean PAUSED = false; // 일시정지 여부 체크


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SD카드에서 파일을 읽어서 확장명 mp3List에 추가하고
        mp3List = new ArrayList<String>(); // mp3 파일만 저장
        mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Music";

        File mydir = new File(mp3Path);
        if ( mydir.isDirectory()){
            File [] files = mydir.listFiles();

            //Music 폴더에 mp3 이외의 파일이 있는 경우에 대한 대비
            for (File file:files){
                String fileName = file.getName();
                String extName = fileName.substring(fileName.length() -3);

                if (extName.equals("mp3")){
                    mp3List.add( fileName);
                }
            }
        }

        //ListView와 mp3List를 연결
        listViewMP3 = (ListView) findViewById(R.id.listViewMP3);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter( adapter );
        listViewMP3.setItemChecked(0, true);
        selectedMP3 = mp3List.get(0);

        // ListView 이벤트 핸들러 작성
        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = adapter.getItem(position);
                stop();
                start();

            }
        });
        tvMP3  = (TextView) findViewById(R.id.tvMP3);
        tvTime = (TextView) findViewById(R.id.tvTime);

        pbMP3  = (SeekBar) findViewById(R.id.pbMP3);
        pbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pbMP3.getProgress();
                mPlayer.stop();
            }
        });
        btnPlay  = (Button) findViewById(R.id.btnPlay);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnStop  = (Button) findViewById(R.id.btnStop);

        //일시정지, 중지버튼 감추기
        btnPause.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);

        //mediaplayer 종료이벤트 핸들러 설정
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stop();
                if (positionListView < adapter.getCount()){
                    listViewMP3.setItemChecked(++positionListView, true);
                    selectedMP3 = adapter.getItem(positionListView);
                    start();
                }
            }
        });
    }
    public void onClick(View view) {
        switch ( view.getId()){
            case R.id.btnPlay:
                start();
                break;

            case R.id.btnPause:
                pause();//일시정지 or 이어듣기
                break;

            case R.id.btnStop:
                stop();
                break;
        }
    }
    private void start(){

        try {
            mPlayer = new MediaPlayer();
            mPlayer.setDataSource( mp3Path + "/" + selectedMP3);
            mPlayer.prepare();
            mPlayer.start();

            btnPlay.setVisibility(View.GONE);
            btnPause.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.VISIBLE);
            pbMP3.setVisibility(View.VISIBLE);

            tvMP3.setText( "실행중인 음악 : "  + selectedMP3);

            //실행중인 음악 출력
            final Handler handler = new Handler();
            Thread thread = new Thread(){
                @Override
                public void run(){
                    if (mPlayer == null)return;
                    pbMP3.setMax(mPlayer.getDuration());

                    for (; mPlayer.isPlaying(); ){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                pbMP3.setProgress(mPlayer.getCurrentPosition());

                                // 진행시간 변경

                                SimpleDateFormat tf = new SimpleDateFormat("mm:ss");
                                String formatted = tf.format(mPlayer.getCurrentPosition());
                                String formatted2 = tf.format(mPlayer.getDuration());
                                tvTime.setText("진행시간 : " +  formatted+" / "+formatted2);
                            }
                        });
                        SystemClock.sleep(200); //ms 생략, 1초 = 1000
                    }
                    //다음곡 실행
                }
            };
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void stop(){
        if (mPlayer != null){
            mPlayer.stop();
            mPlayer.reset();
        }

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);
        pbMP3.setProgress(0);

        //실행중인 음악 출력
        tvMP3.setText( "실행중인 음악 : ");
        //실행중인 음악 시간 출력
        tvTime.setText( "진행시간 : ");
        //
        PAUSED = false;
    }
    private void pause() {
        if (PAUSED == false) { //일시정지
            mPlayer.pause();
            PAUSED = true;
            btnPause.setText("이어듣기");
        }
        else { //이어듣기
            mPlayer.start();
            PAUSED = false;
            btnPause.setText("일시정지");
        }
    }
}
