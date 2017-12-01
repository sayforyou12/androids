package com.android.tabandfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TabFragment1 extends Fragment {

    private View view = null;


    public TabFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.tab_fragment_1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // listview 생성
        //adapter 생성
        //listview와 adapter연결

        //출력데이터 생성
        List<String> list = new ArrayList<>();
        list.add("item 1a");
        list.add("item 1b");
        list.add("item 1c");
        list.add("item 1d");
        list.add("item 1e");
        list.add("item 1f");
        list.add("item 1g");
        list.add("item 1h");
        list.add("item 1i");
        list.add("item 1j");
        list.add("item 1k");
        list.add("item 1l");
        list.add("item 1m");
        list.add("item 1n");
        list.add("item 1o");
        list.add("item 1p");
        list.add("item 1q");
        list.add("item 1r");
        list.add("item 1s");
        list.add("item 1t");
        list.add("item 1u");
        list.add("item 1v");
        list.add("item 1w");
        list.add("item 1x");
        list.add("item 1y");
        list.add("item 1z");




        // listview 생성
        ListView listview = (ListView) view.findViewById(R.id.list_view);

        //adapter 생성
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,list);

        //listview와 adapter 연결
        listview.setAdapter( adapter);

    }

}
