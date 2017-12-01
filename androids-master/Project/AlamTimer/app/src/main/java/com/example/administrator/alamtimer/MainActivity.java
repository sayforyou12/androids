package com.example.administrator.alamtimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {


    Button AlamAdd;
    ListView AlamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost) findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec tab1 = host.newTabSpec("알람");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("알람");
        host.addTab(tab1);

        TabHost.TabSpec tab2 = host.newTabSpec("기록");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("기록");
        host.addTab(tab2);

        TabHost.TabSpec tab3 = host.newTabSpec("스톱워치");
        tab2.setContent(R.id.tab3);
        tab2.setIndicator("스톱워치");
        host.addTab(tab3);

        TabHost.TabSpec tab4 = host.newTabSpec("타이머");
        tab4.setContent(R.id.tab4);
        tab4.setIndicator("타이머");
        host.addTab(tab4);

        AlamAdd = (Button) findViewById(R.id.AlamAdd);
        AlamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addintent = new Intent(getApplicationContext(), AlamAdd.class);
                startActivity(addintent);
            }
        });




    }
}
