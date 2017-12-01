package com.cafe.userapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;

/**
 * Created by Administrator on 2017-08-04.
 */

public class write implements Parcelable {

    String review = "";
    float star;
    String day;


    @Override
    public String toString() {
        return "write{" +
                "Cafeinfo_Frgment='" + review + '\'' +
                ", star=" + star +
                ", day=" + day +
                '}';
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public write(String day) {
        this.day = day;
    }


    public write(){

    }

    protected write(Parcel in) {
        review = in.readString();
        star = in.readFloat();
    }

    public static final Creator<write> CREATOR = new Creator<write>() {
        @Override
        public write createFromParcel(Parcel in) {
            return new write(in);
        }

        @Override
        public write[] newArray(int size) {
            return new write[size];
        }
    };

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public write(String review, float star) {
        this.review = review;
        this.star = star;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(review);
        dest.writeFloat(star);
    }
}