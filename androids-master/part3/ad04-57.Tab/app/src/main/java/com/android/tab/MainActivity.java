package com.android.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    LinearLayout tab1, tab2, tab3, tab4;
    ImageView image1,image2,image3,image4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabHost host = (TabHost) findViewById(R.id.tabhost);
        host.setup();

        TabHost.TabSpec tab1 = null;
        tab1 = host.newTabSpec("강아지");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("강아지");
        host.addTab(tab1);

        TabHost.TabSpec tab2 = host.newTabSpec("고양이");
        tab2 = host.newTabSpec("고양이");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("고양이");
        host.addTab(tab2);

        TabHost.TabSpec tab3 = host.newTabSpec("말");
        tab3 = host.newTabSpec("말");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("말");
        host.addTab(tab3);

        TabHost.TabSpec tab4 = host.newTabSpec("병아리");
        tab4 = host.newTabSpec("병아리");
        tab4.setContent(R.id.tab4);
        tab4.setIndicator("병아리");
        host.addTab(tab4);

    }

}
