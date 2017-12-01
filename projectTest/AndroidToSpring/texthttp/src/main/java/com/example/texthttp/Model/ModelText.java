package com.example.texthttp.Model;

import java.text.Collator;
import java.util.Comparator;

public class ModelText {
    
    private String textview = null;


    public ModelText() {
        super();
    }

    public ModelText(String textview) {
        super();
        this.textview = textview;
    }

    @Override
    public String toString() {
        return "ModelText [textview=" + textview + "]";
    }

    public String getTextview() {
        return textview;
    }

    public void setTextview(String textview) {
        this.textview = textview;
    }
    
}

