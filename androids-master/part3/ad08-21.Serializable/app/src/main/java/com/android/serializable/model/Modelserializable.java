package com.android.serializable.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-06-26.
 */

public class Modelserializable implements Serializable {

    private int intData = 0;
    private String strData = "serialization";

    public Modelserializable() {
    }

    @Override
    public String toString() {
        return "Modelserializable{" +
                "intData=" + intData +
                ", strData='" + strData + '\'' +
                '}';
    }

    public int getIntData() {
        return intData;
    }

    public void setIntData(int intData) {
        this.intData = intData;
    }

    public String getStrData() {
        return strData;
    }

    public void setStrData(String strData) {
        this.strData = strData;
    }
}
