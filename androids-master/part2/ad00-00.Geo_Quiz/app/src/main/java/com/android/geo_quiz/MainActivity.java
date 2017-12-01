package com.android.geo_quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button truebtn1;
    private Button falsebtn1;
    private Button nextbtn1;

    private Question[] QuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_america,true),
            new Question(R.string.question_asia,true)
    };
    TextView questiontext1;
    private int CurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questiontext1 = (TextView) findViewById(R.id.questiontext1);
        int question = QuestionBank[CurrentIndex].getTextResId();
        questiontext1.setText(question);

        truebtn1 = (Button) findViewById(R.id.truebtn1);
        truebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.correct_toast,Toast.LENGTH_SHORT).show();
            }
        });

        falsebtn1 = (Button) findViewById(R.id.falsebtn1);
        falsebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
            }
        });

        nextbtn1 = (Button) findViewById(R.id.nextbtn1);
        nextbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentIndex = (CurrentIndex +1) % QuestionBank.length;
            }
        });


    }
}
