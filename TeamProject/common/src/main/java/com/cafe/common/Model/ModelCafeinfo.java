package com.cafe.common.Model;


import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelCafeinfo implements Serializable {
    
     private Integer cafeno;
     private String brand;
     private String cafename;
     private String cafeaddr;
     private String cafephone;
     private double avg_grade;
     private Integer review_count;
     private Integer like_count;
     private String cafebigtype;
     private String cafesmalltype;
     private String businessnum;
     private String deliveryloc;
     private String opentime;

    @Override
    public String toString() {
        return "ModelCafeinfo{" +
                "cafeno=" + cafeno +
                ", brand='" + brand + '\'' +
                ", cafename='" + cafename + '\'' +
                ", cafeaddr='" + cafeaddr + '\'' +
                ", cafephone='" + cafephone + '\'' +
                ", avg_grade=" + avg_grade +
                ", review_count=" + review_count +
                ", like_count=" + like_count +
                ", cafebigtype='" + cafebigtype + '\'' +
                ", cafesmalltype='" + cafesmalltype + '\'' +
                ", businessnum='" + businessnum + '\'' +
                ", deliveryloc='" + deliveryloc + '\'' +
                ", opentime='" + opentime + '\'' +
                '}';
    }

    public ModelCafeinfo() {
    }

    public ModelCafeinfo(Integer cafeno, String brand, String cafename, String cafeaddr, String cafephone, double avg_grade, Integer review_count, Integer like_count, String cafebigtype, String cafesmalltype, String businessnum, String deliveryloc, String opentime) {
        this.cafeno = cafeno;
        this.brand = brand;
        this.cafename = cafename;
        this.cafeaddr = cafeaddr;
        this.cafephone = cafephone;
        this.avg_grade = avg_grade;
        this.review_count = review_count;
        this.like_count = like_count;
        this.cafebigtype = cafebigtype;
        this.cafesmalltype = cafesmalltype;
        this.businessnum = businessnum;
        this.deliveryloc = deliveryloc;
        this.opentime = opentime;
    }

    public Integer getCafeno() {
        return cafeno;
    }

    public void setCafeno(Integer cafeno) {
        this.cafeno = cafeno;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCafename() {
        return cafename;
    }

    public void setCafename(String cafename) {
        this.cafename = cafename;
    }

    public String getCafeaddr() {
        return cafeaddr;
    }

    public void setCafeaddr(String cafeaddr) {
        this.cafeaddr = cafeaddr;
    }

    public String getCafephone() {
        return cafephone;
    }

    public void setCafephone(String cafephone) {
        this.cafephone = cafephone;
    }

    public double getAvg_grade() {
        return avg_grade;
    }

    public void setAvg_grade(double avg_grade) {
        this.avg_grade = avg_grade;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public void setReview_count(Integer review_count) {
        this.review_count = review_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public String getCafebigtype() {
        return cafebigtype;
    }

    public void setCafebigtype(String cafebigtype) {
        this.cafebigtype = cafebigtype;
    }

    public String getCafesmalltype() {
        return cafesmalltype;
    }

    public void setCafesmalltype(String cafesmalltype) {
        this.cafesmalltype = cafesmalltype;
    }

    public String getBusinessnum() {
        return businessnum;
    }

    public void setBusinessnum(String businessnum) {
        this.businessnum = businessnum;
    }

    public String getDeliveryloc() {
        return deliveryloc;
    }

    public void setDeliveryloc(String deliveryloc) {
        this.deliveryloc = deliveryloc;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }
}
