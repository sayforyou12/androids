package com.cafe.adminapp.cafeinfo;

import android.support.v4.app.Fragment;


public class CafeinfoFragment extends Fragment {


    protected Integer cafeno;

    public Integer getCafeno() {
        return cafeno;
    }

    public void setCafeno(Integer cafeno) {
        this.cafeno = cafeno;
        recall();
    }

    public void recall(){

    }

}
