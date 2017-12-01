package com.cafe.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class CommonActvity extends AppCompatActivity {

    protected SharedPreferences pref = null;
    protected int isheaderimg =0;
    protected String isnickname;
    protected String islevel, isemail;
    protected Integer isuserno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. 공유 프레퍼런스 객체를 얻어온다. /data/data/패키지명/shared_prefs/Setting.xml
        pref = getSharedPreferences("Setting", Context.MODE_PRIVATE);

        // 2. 공유 프레퍼런스를 이용하여 로그인 on/off 설정값을 얻어온다.
        isnickname = pref.getString("nickname_Set",  "").toString();
        islevel = pref.getString("level_Set", "").toString();
        isuserno = pref.getInt("userno_Set", -1);
        isemail = pref.getString("email", "" ).toString();

    }
}
