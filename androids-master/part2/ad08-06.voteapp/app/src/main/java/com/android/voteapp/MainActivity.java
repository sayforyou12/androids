package com.android.voteapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("트와이스 인기투표");

        final  int voteCount[] = new int[9];
        for (int i = 0; i < 9; i++)
            voteCount[i] =  0;

        ImageView image[] = new ImageView[9];
        Integer imageId[] = {R.id.chaeyoeng, R.id.dahyoen, R.id.jihyo, R.id.jungyoen,
                R.id.mina, R.id.momo, R.id.nayoen, R.id.sana, R.id.zewhix};
        final String imgName[] = {"chaeyoeng", "dahyoen", "jihyo", "jungyoen",
                "mina","momo","nayoen","sana","zewhi",};

        for (int i = 0; i < imageId.length; i++){
            final int index;
            index = i;
            image[index] = (ImageView) findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imgName[index]+ " : 총 "+
                    voteCount[index] + " 표", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Button btnFinish = (Button) findViewById(R.id.votebtn);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);
                startActivity(intent);
            }
        });
    }
}
