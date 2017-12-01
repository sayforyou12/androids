package com.android.studentlist;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {



    public static class aa extends AppCompatActivity {
        ArrayList<ModelStudent> data = null;
        private ArrayAdapterEX adapter = null;
        ListView listview;

        class OnItemHandler implements ListView.OnItemClickListener,
                ListView.OnItemLongClickListener,
                ListView.OnItemSelectedListener{

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = "Adapter Item Count : " + adapter.getCount() +" |n";
                msg += "ListView Item Count : " + listview.getCount() + " |n";
                Toast.makeText(aa.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = "onItemLongClick : " + position + "," +id;
                Toast.makeText(aa.this, msg, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String msg = "onItemSlected : " +position+","+id;
                Toast.makeText(aa.this,msg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(aa.this, "onNothingSelected", Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //어댑터에서 사용할 데이터 설정
            data = new ArrayList<ModelStudent>();
            for(int i = 0; i<100; i++){
                ModelStudent student = new ModelStudent();
                student.setName("슈퍼성근"+i);
                student.setNumber("95000" + i);
                student.setDepartment("컴퓨터 공학" + i);
                data.add(student);
            }

            //2. 어댑터를 생성하고 데이터 설정
            adapter = new ArrayAdapterEX(this, R.layout.activity_base_adapter_ex,R.id.nametext,data);


            //3. 리스트뷰에 어댑터 연결
            listview = (ListView) findViewById(R.id.listview);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener( new OnItemHandler());
            listview.setOnItemLongClickListener(new OnItemHandler());
            listview.setOnItemSelectedListener(new OnItemHandler());
            listview.setDivider(new ColorDrawable(Color.GRAY));
            listview.setDividerHeight(3);

            //리스트에 머리 아이템 추가
            View headerView = getLayoutInflater().inflate(R.layout.list_view_header_footer_layout,null);
            TextView headerTv = (TextView) headerView.findViewById(R.id.header_footer_text);
            headerTv.setText("리스트의 시작입니다.");
            listview.addHeaderView(headerView, null, true);

            //리스트에 꼬리 아이템 추가
            View footerView = getLayoutInflater().inflate(R.layout.list_view_header_footer_layout,null);
            TextView footerTv = (TextView) footerView.findViewById(R.id.header_footer_text);
            footerTv.setText("로딩중 ...");
            listview.addFooterView(footerView,null,false);

            listview.setAdapter(adapter);
        }

        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn1:
                    TextView nametext = (TextView) findViewById(R.id.text1);
                    TextView numbertext = (TextView) findViewById(R.id.text2);
                    TextView departmenttext = (TextView) findViewById(R.id.text3);

                    ModelStudent addData = new ModelStudent();
                    addData.setName(nametext.getText().toString());
                    addData.setNumber(numbertext.getText().toString());
                    addData.setDepartment(departmenttext.getText().toString());

                    adapter.add(addData);
                    break;

                case R.id.btn2:

                    EditText text4 = (EditText) findViewById(R.id.text4);
                    Integer index = Integer.parseInt(text4.getText().toString());
                    adapter.remove(data.get(index));

                    break;

                case R.id.btn3:
                    adapter.clear();
                    break;
                case R.id.btn4: {
                    adapter.sort(new Comparator<ModelStudent>() {
                        @Override
                        public int compare(ModelStudent o1, ModelStudent o2) {
                            Collator collator = Collator.getInstance();
                            return collator.compare(o1.getName(), o2.getName());
                        }
                    });
                    break;
                }

            }
        }

    }
}
