package com.android.cursorsinglegirl;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017-07-12.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context){
        super(context, "groupDB" , null, 22);
    }


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "";
        query += "CREATE TABLE groupTBL (                       \r\n";
        query += " _id integer primary key autoincrement        \r\n";
        query += " , gName text                                 \r\n";
        query += " , gNumber integer                              \r\n";
        query += "      )                                       \r\n";

        db.execSQL(query); // 테이블 생성

        //기초데이터 입력
        db.execSQL(" insert into groupTBL(gName, gNumber) values(?, ?) " ,new String[]{"aaa", "1"});
        db.execSQL(" insert into groupTBL(gName, gNumber) values(?, ?) " ,new String[]{"bbb", "2"});
        db.execSQL(" insert into groupTBL(gName, gNumber) values(?, ?) " ,new String[]{"ccc", "3"});
        db.execSQL(" insert into groupTBL(gName, gNumber) values(?, ?) " ,new String[]{"ddd", "4"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS groupTBL ");
        onCreate(db);

    }
}
