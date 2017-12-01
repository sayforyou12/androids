package com.cafe.adminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cafe.adminapp.adapter.CafeList_Adapter;
import com.cafe.common.HttpAddrCafeList;
import com.cafe.common.Model.ModelCafeinfo;

import java.util.ArrayList;
import java.util.List;

public class AddrSearchList extends AppCompatActivity {

    private ListView addrlist;
    private List<ModelCafeinfo> cafelist;
    private ModelCafeinfo cafeinfo = new ModelCafeinfo();
    private CafeList_Adapter adapter;
    private EditText edit_cafe_addr2;
    private Button btn_cafe_addr2;
    private String addr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addr_search_list);

        Intent intent = getIntent();
        addr = intent.getStringExtra("addr".toString());

        addrlist = (ListView) findViewById(R.id.addrlist);

        cafelist = new ArrayList<>();

        adapter = new CafeList_Adapter(getApplicationContext(), R.layout.activity_cafelist_item, R.id.cafe_name, cafelist );

        addrlist.setAdapter(adapter);
        new HttpAddr().execute(addr);

        edit_cafe_addr2 = (EditText) findViewById(R.id.edit_cafe_addr2);
        btn_cafe_addr2 = (Button) findViewById(R.id.btn_cafe_addr2);

        btn_cafe_addr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addr = edit_cafe_addr2.getText().toString();
                new HttpAddr().execute(addr);
            }
        });
    }
    public class HttpAddr extends AsyncTask<String, Integer, List<ModelCafeinfo>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(AddrSearchList.this);
            waitDlg.setMessage("버전 확인 중");
            waitDlg.show();
        }

        @Override
        protected List<ModelCafeinfo> doInBackground(String... strings) {

            String addr = strings[0].toString();

            cafelist = null;
            try {
                cafelist = new HttpAddrCafeList().addr(addr);
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
        protected void onPostExecute(List<ModelCafeinfo> s) {
            super.onPostExecute(s);

            adapter.clear();
            adapter.addAll(cafelist);
            adapter.notifyDataSetChanged();

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            super.onPostExecute(s);
        }
    }
}
