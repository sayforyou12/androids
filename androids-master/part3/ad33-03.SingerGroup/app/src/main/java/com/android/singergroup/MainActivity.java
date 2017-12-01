package com.android.singergroup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2,btn3,btn4,btn5;
    EditText edittxt1, edittxt2;
    SQLiteDatabase sqlDB;
    myDBHelper myDBHelper;
    ListView list_View =null;
    CursorAdapter mAdapter = null;
    Cursor data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[]columns = new String[]{"name", "member"};

        setContentView(R.layout.activity_main);
        setTitle("가수그룹 관리DB");

        String q = "SELECT * FROM groupTBL";
        data = sqlDB.rawQuery(q, null);


        // 2. 어댑터 생성하고 데이터 설정
        mAdapter = new CursorAdapterEX(this, data, 0);

        //3. 리스트뷰에 어댑터 설정
        list_View = (ListView) findViewById(R.id.list_view);
        list_View.setAdapter(mAdapter);

        edittxt1 = (EditText) findViewById(R.id.edtName);
        edittxt2 = (EditText) findViewById(R.id.edtNumber);

        btn1 = (Button) findViewById(R.id.btnInit);
        btn2 = (Button) findViewById(R.id.btnInsert);
        btn3 = (Button) findViewById(R.id.btnUpdate);
        btn4 = (Button) findViewById(R.id.btnSelect);
        btn5 = (Button) findViewById(R.id.btnSort);
        myDBHelper = new myDBHelper(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('"
                        + edittxt1.getText().toString()+"',"
                        + edittxt2.getText().toString()+");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨",Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getReadableDatabase();

                data = sqlDB.rawQuery("SELECT * FROM groupTBL", null);

                String strnames = "그룹 이름" + "\r\n" + "-----" + "\r\n";
                String strnumbers = "인원" + "\r\n" + "-----" + "\r\n";


                while (data.moveToNext()){
                    strnames += data.getString(0) + "\r\n";
                    strnumbers += data.getString(1) + "\r\n";
                }
                data.close();
                sqlDB.close();
            }
        });


    }

    public class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context) {
            super(context, "groupDB", null, 3);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL(_id integer primary key autoincrement gName CHAR(20) PRIMARY KEY, gNumber INTERGER");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}
