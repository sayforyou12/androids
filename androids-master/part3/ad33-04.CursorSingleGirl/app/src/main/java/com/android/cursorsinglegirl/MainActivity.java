package com.android.cursorsinglegirl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView  = null;
    EditText edtName   = null;
    EditText edtNumber = null;
    RadioButton rb_name, rb_number, rb_ASP, rb_DESC = null;

    private SQLiteDatabase db = null;
    CursorAdapterEX adapterEX = null;

    private long _id = -1;
    AlertDialog.Builder dlg = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView  = (ListView) findViewById(R.id.list_view);
        this.edtName   = (EditText) findViewById(R.id.edt_Name);
        this.edtNumber = (EditText) findViewById(R.id.edt_Number);

        rb_name = (RadioButton) findViewById(R.id.rb_name);
        rb_number = (RadioButton) findViewById(R.id.rb_number);
        rb_ASP = (RadioButton) findViewById(R.id.rb_ASP);
        rb_DESC = (RadioButton) findViewById(R.id.rb_DESC);





        //1 DBHelper 생성
        MyDBHelper dbHelper = new MyDBHelper(this);

        //2 DB open
        this.db = dbHelper.getWritableDatabase();

        //3.1 1테이블에서 데이터 가져오기
        // db.rawQuery(" select * from groupTBL where _id=? and gName=?", new String[]{"1", "aaa"});
        Cursor cursor = db.rawQuery(" select * from groupTBL ", null);

        //3.2 Adapter 생성
        this.adapterEX = new CursorAdapterEX(getApplicationContext(), cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //3.3 ListView 와 Adapter 연결
        this.listView.setAdapter( this.adapterEX);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // id = groupTBL._id
                Cursor cursor = db.rawQuery( " select * from groupTBL where _id=?", new String[]{String.valueOf(id)});

                //커서위치 0번으로 이동
                cursor.moveToFirst();

                String name   = cursor.getString( cursor.getColumnIndex( "gName"   ));
                String number = cursor.getString( cursor.getColumnIndex( "gNumber" ));

                edtName.setText( name   );
                edtNumber.setText( number );

                MainActivity.this._id = id;
            }
        });

        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // id = groupTBL._id
                db.rawQuery( " delete from groupTBL where _id=?", new String[]{String.valueOf(id)});

                edtName  .setText( "" );
                edtNumber.setText( "" );

                //listView 새로고침
                adapterEX.onContentChanged();

                return true;
            }
        });
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_Init:
                db.execSQL(" delete from groupTBL ");
                this.adapterEX.onContentChanged();
                break;

            case R.id.btn_Insert:
                this.adapterEX.onContentChanged();
                break;

            case R.id.btn_Update:
                db.execSQL( " update groupTBL set gName=?, gNumber=? where _id=?", new String[]{
                        edtName.getText().toString(), edtNumber.getText().toString(), String.valueOf(_id)} );
                this.adapterEX.onContentChanged();
                break;

            case R.id.btn_Select:
                // inflation
                final View dialogView = View.inflate(this, R.layout.dialog_search, null);
                dlg = new AlertDialog.Builder(this);
                dlg.setTitle("검색조건을 입력하시오");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView( dialogView);
                dlg.setPositiveButton("검색", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name   = (EditText) dialogView.findViewById(R.id.dialog_name);
                        EditText number = (EditText) dialogView.findViewById(R.id.dialog_number);

                        String query = "";
                                                                    query += String.format(" select * from groupTBL \r\n");
                                                                    query += String.format(" where 1 =1             \r\n");
                        if ( !name.getText().toString().isEmpty())  query += String.format(" and gName   = '%s'     \r\n", name.getText().toString()  );
                        if ( !number.getText().toString().isEmpty())query += String.format(" and gNumber =  %s      \r\n", number.getText().toString());

                        Cursor cursor = db.rawQuery(query, null);
                        adapterEX.changeCursor(cursor);

                    }
                });
                dlg.setNegativeButton("닫기", null);
                dlg.show();
                break;

            case R.id.btn_Sort:
                final View sort = View.inflate(this, R.layout.btnsort, null);
                final RadioGroup rg1 = (RadioGroup) sort.findViewById(R.id.rg1);
                final RadioGroup rg2 = (RadioGroup) sort.findViewById(R.id.rg2);

                final SharedPreferences mPref = getSharedPreferences( "Setting", Context.MODE_PRIVATE);
                rg1. check( mPref.getInt("rg1", -1));
                rg2. check( mPref.getInt("rg2", -1));

                dlg = new AlertDialog.Builder(this);
                dlg.setTitle("검색조건을 입력하시오");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView( sort);



                dlg.setPositiveButton("검색", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor prefEditor = mPref.edit();
                        prefEditor.putInt( "rg1", rg1.getCheckedRadioButtonId() );
                        prefEditor.putInt( "rg2", rg2.getCheckedRadioButtonId() );
                        prefEditor.apply(); // Setting.xml에 저장

                        EditText name   = (EditText) sort.findViewById(R.id.sort_edtname);
                        EditText number = (EditText) sort.findViewById(R.id.sort_edtnumber);

                        Cursor data = null;

                        String query = "";
                                                                    query += String.format(" select * from groupTBL \r\n");
                                                                    query += String.format(" where 1 =1             \r\n");
                        if ( !name.getText().toString().isEmpty())  query += String.format(" and gName   = '%s'     \r\n", name.getText().toString()  );
                        if ( !number.getText().toString().isEmpty())query += String.format(" and gNumber =  %s      \r\n", number.getText().toString());

                        if (rb_name.isChecked()) {
                            if (rb_ASP.isChecked()){
                                data = db.rawQuery("select * from groupTBL order by db ASP", null);
                            }
                            else if (rb_DESC.isChecked()){
                                data = db.rawQuery("select * from groupTBL order by db DESC", null);
                            }
                        }

                        if (rb_name != null);
                        Cursor cursor = db.rawQuery(query, null);
                        adapterEX.changeCursor(cursor);

                    }
                });
                dlg.setNegativeButton("닫기", null);
                dlg.show();
                break;

        }
    }



    public class CursorAdapterEX extends CursorAdapter {

        private Context context  = null;
        private LayoutInflater inflater = null;
        private Handler handler  = null;


        private class ViewHolder{
            TextView txtName   = null;
            TextView txtNumber = null;

            public ViewHolder(){
                super();
            }


        }

        public CursorAdapterEX(Context context, Cursor c, int flags) {
            super(context, c, flags);

            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.handler = new Handler();
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = inflater.inflate(R.layout.listview_item,parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtName   = (TextView) view.findViewById(R.id.txt_name    );
            viewHolder.txtNumber = (TextView) view.findViewById(R.id.txt_number  );
            view.setTag(viewHolder);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            ViewHolder viewHolder = (ViewHolder) view.getTag();

            viewHolder.txtName  .setText(cursor.getString( cursor.getColumnIndex("gName" )));
            viewHolder.txtNumber.setText(cursor.getString(cursor.getColumnIndex("gNumber")));
        }

        @Override
        protected void onContentChanged() {
            super.onContentChanged();

            Thread thread = new Thread(){
                //Cursor cursor =
                public void run(){
                    final Cursor cursor =db.rawQuery(" select * from groupTBL " , null);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            changeCursor( cursor );

                        }
                    });

                }
            };
            thread.start();
        }


    }
}
