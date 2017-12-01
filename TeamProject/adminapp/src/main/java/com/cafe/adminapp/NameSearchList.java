package com.cafe.adminapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cafe.adminapp.adapter.CafeList_Adapter;
import com.cafe.common.HttpNameCafeList;
import com.cafe.common.Model.ModelCafeinfo;

import java.util.ArrayList;
import java.util.List;

public class NameSearchList extends AppCompatActivity {

    private ListView namelist;
    private List<ModelCafeinfo> cafelist;
    private ModelCafeinfo cafeinfo = new ModelCafeinfo();
    private CafeList_Adapter adapter;
    private EditText edit_cafe_name2;
    private Button btn_cafe_name2;
    private String name = "";

    private ArrayList Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_search_list);

        Intent intent = getIntent();
        name = intent.getStringExtra("name".toString());

        namelist = (ListView) findViewById(R.id.namelist);

        cafelist = new ArrayList<>();

        adapter = new CafeList_Adapter(getApplicationContext(), R.layout.activity_cafelist_item, R.id.cafe_name, cafelist );

        namelist.setAdapter(adapter);
        new HttpName().execute(name);

        edit_cafe_name2 = (EditText) findViewById(R.id.edit_cafe_name2);
        btn_cafe_name2 = (Button) findViewById(R.id.btn_cafe_name2);

        btn_cafe_name2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edit_cafe_name2.getText().toString();
                new HttpName().execute(name);
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

    public class HttpName extends AsyncTask<String, Integer, List<ModelCafeinfo>> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(NameSearchList.this);
            waitDlg.setMessage("버전 확인 중");
            waitDlg.show();
        }

        @Override
        protected List<ModelCafeinfo> doInBackground(String... strings) {

            String name = strings[0].toString();

            cafelist = null;
            try {
                cafelist = new HttpNameCafeList().name(name);
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

            cafelist = s;
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
