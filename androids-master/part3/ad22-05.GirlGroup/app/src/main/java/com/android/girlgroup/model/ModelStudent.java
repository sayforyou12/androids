package com.android.girlgroup.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017-06-29.
 */

public class ModelStudent implements Parcelable{

    String name = ""; //이름
    String phonenumber = ""; //폰번호
    String number = ""; //번호

    public ModelStudent() {
    }

    @Override
    public String toString() {
        return "ModelStudent{" +
                "name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
