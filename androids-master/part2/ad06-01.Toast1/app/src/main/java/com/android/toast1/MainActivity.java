package com.android.toast1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    int i = 0;
    Toast toast = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(getApplicationContext(),"Hello"+ i++, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(getApplicationContext(),"toast"+i++, Toast.LENGTH_SHORT);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                int xOffset = (int) (Math.random()*displayMetrics.widthPixels);
                int yOffset = (int) (Math.random()*displayMetrics.heightPixels);


                toast.setGravity(Gravity.CENTER,xOffset,yOffset);
                toast.show();

            }
        });
    }
}
