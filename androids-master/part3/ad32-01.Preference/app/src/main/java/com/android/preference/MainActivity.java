package com.android.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox check1 = null;
    SharedPreferences Pref = null;
    Button endbtn = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check1 = (CheckBox) findViewById(R.id.check1);
        endbtn = (Button) findViewById(R.id.endbtn);

        endbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //1. 공유 프레퍼런스 객체 얻어오기(/data/data/패키지명/share_prefs/Setting.xml
        Pref = getSharedPreferences("Setting", Context.MODE_PRIVATE);

        //2. 공유 프레퍼런스를 이용하여 사운드 온오프 설정값을 얻어온다
        boolean isSountdOn = Pref.getBoolean("SOUND_SET", true);

        //3.현재 사운드 설정값을 체크박스에 반영한다.
        check1.setChecked(isSountdOn);
    }

    public void onClick(View v){
        // 공유 프레버런스에 사운드 설정값을 저장한다.
        SharedPreferences.Editor prefEditor = Pref.edit();
        prefEditor.putBoolean("SOUND_SET", check1.isChecked());
        prefEditor.apply();
    }

}
