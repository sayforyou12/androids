package com.android.geo_quiz;

/**
 * Created by Administrator on 2017-06-30.
 */

public class Question {
    private int TextResId;
    private boolean AnswerTrue;

    public  Question(int textResId, boolean answerTrue){

        TextResId = textResId;
        AnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return TextResId;
    }

    public void setTextResId(int textResId) {
        TextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return AnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        AnswerTrue = answerTrue;
    }


}