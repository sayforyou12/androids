package com.cafe.adminapp.cafelist;

import android.support.v4.app.Fragment;



public class CafeListFragment extends Fragment {
    protected String orderKind="";

    public String getOrderKind() {
        return orderKind;
    }

    public void setOrderKind(String orderKind) {
        this.orderKind = orderKind;
        recall();

        // 메서드 호출
    }
    public void recall(){

    }
}
