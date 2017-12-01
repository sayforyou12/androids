package com.android.autoincrementscrollviewactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private ScrollView scroll1;
    private LinearLayoutCompat lay1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lay1 = (LinearLayoutCompat) findViewById(R.id.lay1);
        btn1 = (Button) findViewById(R.id.btn1);
        scroll1 = (ScrollView) findViewById(R.id.scroll1);

        scroll1.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int t = lay1.getHeight();
                int y = scroll1.getScrollY();
                int h = scroll1.getHeight();

                Log.d("aaaa", String.format("t:%d, y:%d, h:%d", t, y, h));

                if(lay1.getHeight() == scroll1.getHeight()+scroll1.getHeight()+scroll1.getScrollY()){

                    for(int i = 1; i<=5; i++){

                    }

                }

            }
        });
    }
}
