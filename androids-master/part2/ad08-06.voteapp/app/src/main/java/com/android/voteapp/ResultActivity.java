package com.android.voteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private ImageView topimg;
    private TextView toptext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("투표결과");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];

        Integer tvID[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4,
                R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
        Integer rbarID[] = {R.id.rbar1, R.id.rbar2,R.id.rbar3,R.id.rbar4,R.id.rbar5,
                R.id.rbar6,R.id.rbar7,R.id.rbar8,R.id.rbar9};
        Integer img[] = {R.drawable.chaeyoeng, R.drawable.dahyoen, R.drawable.jihyo,
                R.drawable.jungyoen, R.drawable.mina, R.drawable.momo, R.drawable.nayoen,
                R.drawable.sana, R.drawable.zewhi};

        for(int i = 0;  i < voteResult.length; i++){
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }

        for(int i = 0; i < voteResult.length; i++){
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float)voteResult[i]);
        }

        topimg = (ImageView) findViewById(R.id.topimg);
        toptext = (TextView) findViewById(R.id.toptext);

        int top = voteResult[0];
        for (int i = 1; i < voteResult.length; i++){
            if(voteResult[i] >= top){
                top = voteResult[i];
            }
        }
        for(int j = 0; j < voteResult.length; j++){
            if(voteResult[j] == top){
                topimg.setImageResource(img[j]);
                toptext.setText(imageName[j]);
            }
        }

        Button returnbtn = (Button) findViewById(R.id.returnbtn);
        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
