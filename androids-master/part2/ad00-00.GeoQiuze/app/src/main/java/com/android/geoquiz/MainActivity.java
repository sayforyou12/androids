package com.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.geoquiz.R;

public class MainActivity extends AppCompatActivity {
    private Button truebtn;
    private Button falsebtn;
    private ImageButton nextbtn;
    private TextView questiontext;
    private Button cheatbtn;
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean IsCheater;

    private Question[] questionbank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_america, true),
            new Question(R.string.question_asia, true),
    };
    private int currentIndex = 0;

    private void updateQuestion(){
        int question = questionbank[currentIndex].getTextResId();
        questiontext.setText(question);
    }

    private void checkAnswer(boolean userPresserTrue){
        boolean answerIsTrue=questionbank[currentIndex].isAnswerTrue();

        int messageResId = 0;
        if (IsCheater){
            messageResId = R.string.judgment_toast;
        }
        else{
            if (userPresserTrue == answerIsTrue){
                messageResId = R.string.correct_toast;
            }
            else{
                messageResId = R.string.incorrect_toast;
            }

        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        questiontext = (TextView) findViewById(R.id.questiontext);
        final int question = questionbank[currentIndex].getTextResId();
        questiontext.setText(question);

        truebtn = (Button) findViewById(R.id.truebtn);
        truebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });
        falsebtn = (Button) findViewById(R.id.falsebtn);
        falsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

            }
        });
        nextbtn = (ImageButton) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IsCheater = false;
                currentIndex = (currentIndex + 1) % questionbank.length;
                updateQuestion();
            }
        });

        cheatbtn = (Button) findViewById(R.id.cheatbtn);
        cheatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerITrue = questionbank[currentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerITrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
            IsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart() called");
        super.onStart();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() called");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }

}
