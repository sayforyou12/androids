package com.android.card2;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView mainCard, card1, card2, card3, card4, card5, card6, card7, card8, card9 = null;

    Button start;
    int[]img = {R.drawable.coka, R.drawable.dalma, R.drawable.poodle};


    int coka = R.drawable.coka;
    int dalma = R.drawable.dalma;
    int poodle = R.drawable.poodle;

    int arr[] = new int[3];
    int arr1[] = new int[3];


    Random random = new Random();
    int ran = random.nextInt(img.length);

    /*final ImageView[] imv = {card1, card2, card3, card4, card5, card6, card7, card8, card9};*/
    /*final int[] imv1 = {R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6, R.id.card7,
            R.id.card8, R.id.card9};*/
    ArrayList<ImageView> imv2 = new ArrayList<ImageView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCard = (ImageView) findViewById(R.id.mainCard);
        card1 = (ImageView) findViewById(R.id.card1);
        card2 = (ImageView) findViewById(R.id.card2);
        card3 = (ImageView) findViewById(R.id.card3);
        card4 = (ImageView) findViewById(R.id.card4);
        card5 = (ImageView) findViewById(R.id.card5);
        card6 = (ImageView) findViewById(R.id.card6);
        card7 = (ImageView) findViewById(R.id.card7);
        card8 = (ImageView) findViewById(R.id.card8);
        card9 = (ImageView) findViewById(R.id.card9);

        start = (Button) findViewById(R.id.start);

        mainCard.setImageResource(img[ran]);

        imv2.add(card1);
        imv2.add(card2);
        imv2.add(card3);
        imv2.add(card4);
        imv2.add(card5);
        imv2.add(card6);
        imv2.add(card7);
        imv2.add(card8);
        imv2.add(card9);

        Collections.shuffle(imv2);

        imv2.get(0).setImageResource(coka);
        imv2.get(1).setImageResource(coka);
        imv2.get(2).setImageResource(coka);
        imv2.get(3).setImageResource(dalma);
        imv2.get(4).setImageResource(dalma);
        imv2.get(5).setImageResource(dalma);
        imv2.get(6).setImageResource(poodle);
        imv2.get(7).setImageResource(poodle);
        imv2.get(8).setImageResource(poodle);

        Drawable main = mainCard.getDrawable();
        Drawable c1 = imv2.get(0).getDrawable();
        Drawable c2 = imv2.get(1).getDrawable();
        Drawable c3 = imv2.get(2).getDrawable();
        Drawable c4 = imv2.get(3).getDrawable();
        Drawable c5 = imv2.get(4).getDrawable();
        Drawable c6 = imv2.get(5).getDrawable();
        Drawable c7 = imv2.get(6).getDrawable();
        Drawable c8 = imv2.get(7).getDrawable();
        Drawable c9 = imv2.get(8).getDrawable();

        final Bitmap mainbit = ((BitmapDrawable)main).getBitmap();
        final Bitmap c1bit = ((BitmapDrawable)c1).getBitmap();
        final Bitmap c2bit = ((BitmapDrawable)c2).getBitmap();
        final Bitmap c3bit = ((BitmapDrawable)c3).getBitmap();
        final Bitmap c4bit = ((BitmapDrawable)c4).getBitmap();
        final Bitmap c5bit = ((BitmapDrawable)c5).getBitmap();
        final Bitmap c6bit = ((BitmapDrawable)c6).getBitmap();
        final Bitmap c7bit = ((BitmapDrawable)c7).getBitmap();
        final Bitmap c8bit = ((BitmapDrawable)c8).getBitmap();
        final Bitmap c9bit = ((BitmapDrawable)c9).getBitmap();


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainbit.equals(c1bit)){
                    Toast.makeText(MainActivity.this,"yes",Toast.LENGTH_SHORT).show();
                    card1.setColorFilter(R.color.colorPrimaryDark);
                    card1.setClickable(false);
                }
                else if (!mainbit.equals(c1bit)){
                    Toast.makeText(MainActivity.this,"No", Toast.LENGTH_SHORT).show();
                    card1.setColorFilter(R.color.colorPrimaryDark);
                    card1.setClickable(false);
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        // imv2는 섞였지만, imv1은 그대로.

    }


        /*if (imv[dognine].getDrawable()!= null){*/
        /*start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int dognine = 0;
                int temp;
                for (int i = 0; i < imv1.length; i++){
                    dognine = i;
                    imv1[i] = random.nextInt(imv.length);
                    for (int j = 0; j < i; j++) {
                        if (imv[i] == imv[j]) {
                            i--;
                            break;

                            //imv1(int)가 섞였지, imv(imageview)가 섞인건 아니다.
                        }
                    }
                }
            }
        });
*/


                /*for (int i = 0; i < imv1.length+1; i++){
                    for (int j = 0; j < imv1.length+1; j++){
                        temp = imv1[i];
                        imv1[i] = imv1[j];
                        imv1[j] = temp;
                    }
                }*/



                //coka이미지가 없는경우, 혹은 coka 이미지 3개보다 적은 경우
                /*if (!imv[dognine].getDrawable().equals(coka) || imv1[dognine] != coka*3){
                    for (int ra = 0; ra < arr.length; ra++){
                /*//*three = i;*//**//*
                        //랜덤값 선언
                        arr[ra] = random.nextInt(imv.length);

                        //중복값 걸러내기
                        for (int j = 0; j < ra; j++) {
                            if (arr[ra] == arr[j]) {
                                ra--;
                            }
                        }
                        //선언된 랜덤값에 coka이미지 부여
                        imv[arr[ra]].setImageResource(coka);
                        nullcoka = ra;
                    }
                }

                if (!imv[dognine].getDrawable().equals(dalma) || imv1[dognine] != dalma*3){
                    for (int nd = 0; nd < arr1.length; nd++){
                        arr1[nd] = random.nextInt(imv.length-arr[nullcoka]);
                        for (int j = 0; j < nd; j++) {
                            //중복되지않도록 걸러냄
                            if (arr1[nd] == arr[nullcoka] || arr1[nd] == arr1[j]) {
                                nd--;
                                //coka의 자리와 중복되지 않도록 걸러냄(총 2번 걸러냄)
                                if (arr1[nd] == arr[nullcoka] || arr1[nd] == arr1[j]){
                                    nd--;
                                    if(arr1[nd] == arr[nullcoka] || arr1[nd] == arr1[j]){
                                        nd--;
                                    }
                                }
                                break;
                            }

                        }
                        imv[arr1[nd]].setImageResource(dalma);
                    }
                }*/

}
