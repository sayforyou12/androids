package com.android.serializable.model;

import android.os.Parcel;
import android.os.Parcelable;

import static android.R.attr.src;

public class ModelParcelable implements Parcelable {

    private int intData = 0;
    private String strData = "Superdroid";

    public ModelParcelable() {
    }

    @Override
    public String toString() {
        return "ModelParcelable{" +
                "intData=" + intData +
                ", strData='" + strData + '\'' +
                '}';
    }

    public ModelParcelable(int intData, String strData) {
        this.intData = intData;
        this.strData = strData;
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

    protected ModelParcelable(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(intData);
        dest.writeString(strData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelParcelable> CREATOR = new Creator<ModelParcelable>() {
        @Override
        public ModelParcelable createFromParcel(Parcel src) {
            ModelParcelable sampleData = new ModelParcelable();
            sampleData.setIntData(src.readInt());
            sampleData.setStrData(src.readString());

            return sampleData;
        }


        @Override
        public ModelParcelable[] newArray(int size) {
            return null;
        }
    };
}
