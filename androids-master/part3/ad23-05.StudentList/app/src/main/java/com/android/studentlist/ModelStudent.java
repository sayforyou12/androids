package com.android.studentlist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017-06-29.
 */

public class ModelStudent implements Parcelable{

    String name = ""; //이름
    String number = ""; //학번
    String department = ""; //학과

    public ModelStudent() {
    }

    @Override
    public String toString() {
        return "ModelStudent{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    protected ModelStudent(Parcel in) {
    }

    public static final Creator<ModelStudent> CREATOR = new Creator<ModelStudent>() {
        @Override
        public ModelStudent createFromParcel(Parcel in) {
            return new ModelStudent(in);
        }

        @Override
        public ModelStudent[] newArray(int size) {
            return new ModelStudent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
