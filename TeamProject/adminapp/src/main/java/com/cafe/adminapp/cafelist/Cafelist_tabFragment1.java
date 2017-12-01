package com.cafe.adminapp.cafelist;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Intent;

import com.cafe.adminapp.R;
import com.cafe.adminapp.cafeinfo.FragmentInfoActivity;
import com.cafe.common.HttpCafeList;
import com.cafe.adminapp.adapter.CafeList_Adapter;
import com.cafe.common.Model.ModelCafeinfo;

import java.util.ArrayList;
import java.util.List;

public class Cafelist_tabFragment1 extends CafeListFragment {

    private View view = null;

    private CafeList_Adapter adapterEx;
    private List<ModelCafeinfo> cafelist;
    private ModelCafeinfo cafeinfo = new ModelCafeinfo();

    public Cafelist_tabFragment1() {
    }

    @Override
    public void recall() {
        super.recall();
        new Cafelist_tabFragment1.Httplist().execute(cafeinfo, getOrderKind());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cafelist_tab_fragment_1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 출력 데이터 생성
        // ListView 생성
        // Adapter 생성
        // ListView와 Adapter 연결

        // 출력 데이터 생성

        // ListView 생성
        ListView listView = (ListView) view.findViewById(R.id.fraglist1);

        // 출력 데이터 생성
        cafelist = new ArrayList<>();

        // Adapter 생성
        adapterEx = new CafeList_Adapter(getContext(), R.layout.activity_cafelist_item, R.id.cafe_name, cafelist);

        cafeinfo.setCafebigtype("카페");

        // ListView와 Adapter 연결
        listView.setAdapter(adapterEx);
        new Cafelist_tabFragment1.Httplist().execute(cafeinfo, "cafename");

        // 아이템 클릭 이벤트 (cafeinfo 모델값을 넘겨준다.)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), FragmentInfoActivity.class);
                cafeinfo = cafelist.get(position);
                intent.putExtra("cafeinfo", cafeinfo);
                startActivity(intent);

            }
        });

    }

    // Arrays List Adapter 연결
    class OnItemHandler implements ListView.OnItemClickListener, ListView.OnItemLongClickListener, ListView.OnItemSelectedListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //String msg = "Adapter Item Count : " + mAdapter.getCount() + "\n";
            //msg += "ListView Item Count : " + parent.getCount();
            //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

            ModelCafeinfo s = (ModelCafeinfo) parent.getItemAtPosition(position);
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // Http List DB 가져오기
    public class Httplist extends AsyncTask<Object, Integer, List<ModelCafeinfo>> {

        private ProgressDialog waitDlg = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected List<ModelCafeinfo> doInBackground(Object... params) {

            try {
                cafelist = new HttpCafeList().itemlist((ModelCafeinfo) params[0], (String) params[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return cafelist;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ModelCafeinfo> modelCafeinfos) {
            super.onPostExecute(modelCafeinfos);
            // 1.
            cafelist = modelCafeinfos;
            adapterEx.clear();
            adapterEx.addAll(cafelist);
            adapterEx.notifyDataSetChanged();

        }
    }
}
