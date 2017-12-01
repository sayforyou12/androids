package com.android.alertdialog;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Alterbtn, Confirmbtn, Listbtn, Radiobtn, Checkbtn;
    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Alterbtn = (Button) findViewById(R.id.Alterbtn);
        Confirmbtn = (Button) findViewById(R.id.Confirmbtn);
        Listbtn = (Button) findViewById(R.id.Listbtn);
        Radiobtn = (Button) findViewById(R.id.Radiobtn);
        Checkbtn = (Button) findViewById(R.id.Checkbtn);
        text1 = (EditText) findViewById(R.id.text1);
        final String[] result = {null};

        Alterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("제목입니다.");
                dlg.setMessage("이곳이 내용입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
        });

        Confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("제목입니다.");
                dlg.setMessage("이곳이 내용입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setPositiveButton("확인",null);
                dlg.setNegativeButton("닫기",null);
                dlg.show();
            }
        });

        Listbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] versionArray = new String[]{"롤리팝", "마시멜로", "누가"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("좋아하는 버전은?");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setItems(versionArray,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                text1.setText(versionArray[which]);

                            }

                });
                dlg.setPositiveButton("닫기",null);
                dlg.show();

            }
        });

        Radiobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] versionArray = new String[]{"롤리팝", "마시멜로", "누가"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setSingleChoiceItems(versionArray, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                text1.setText(versionArray[which]);
                            }
                        });
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });

        Checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                text1.setText(null);


                final String[] versionArray = new String[]{"롤리팝", "마시멜로", "누가"};
                final boolean[] checkArray = new boolean[]{false, false, false};
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("좋아하는 버전은?");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        text1.setText(versionArray[which]);
                    }
                });

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        List<Integer>truelist = new ArrayList<Integer>();
                        for(int i = 0; i < checkArray.length; i++){
                            if(checkArray[i]){
                                truelist.add(i);
                            }
                        }
                        String color = "";
                        for(int i = 0; i <truelist.size(); i++){
                            color = color + versionArray[truelist.get(i)];
                            if(i != truelist.size()-1){
                                color = color+" ";
                            }
                            else {
                                color += " ";
                            }
                        }
                        text1.setText(color);
                    }

                });

                dlg.show();
            }
        });
    }
}
