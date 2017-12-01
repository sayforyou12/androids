package com.android.tabhost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml파일에서 tabhost 가져오기.
        TabHost host = (TabHost) findViewById(android.R.id.tabhost);
        host.setup();

        // tabspec 생성
        TabHost.TabSpec tab1 = host.newTabSpec("TAB ONE");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("TAB ONE");
        host.addTab(tab1);

        TabHost.TabSpec tab2 = host.newTabSpec("TAB TWO");
        tab2 = host.newTabSpec("TAB TWO");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("TAB TWO");
        host.addTab(tab2);

        TabHost.TabSpec tab3 = host.newTabSpec("TAB THREE");
        tab3 = host.newTabSpec("TAB THREE");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("TAB THREE");
        host.addTab(tab3);
    }
}
