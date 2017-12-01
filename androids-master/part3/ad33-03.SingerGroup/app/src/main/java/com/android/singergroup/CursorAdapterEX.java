package com.android.singergroup;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class CursorAdapterEX extends CursorAdapter{
    private static Uri PROVIDER_URI = Uri.parse("content://com.superdroid.StudentsProvider/students");

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;

    public void clear(){
        mContext.getContentResolver().delete(PROVIDER_URI, null, null);
    }

    public void add(String name, Integer member){
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("member", member);

        mContext.getContentResolver().insert(PROVIDER_URI, values);
    }


    public void remove(String name){
        Uri deleteUri = ContentUris.withAppendedId(PROVIDER_URI, 0);
        mContext.getContentResolver().delete( deleteUri, null, null);

    }


    public CursorAdapterEX(Context context, Cursor c) {
        super(context, c);
    }

    public CursorAdapterEX(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public CursorAdapterEX(Context context, Cursor c, int flags) {
        super(context, c, flags);

        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

    }
    class ViewHolder{
        EditText nametv, membertv;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View itemLayout = mLayoutInflater.inflate(R.layout.inflater_singer_group_item, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.nametv = (EditText) itemLayout.findViewById(R.id.nametv);
        viewHolder.membertv = (EditText) itemLayout.findViewById(R.id.memvertv);

        itemLayout.setTag(viewHolder);
        return itemLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String nametv = cursor.getString( cursor.getColumnIndex("name"));
        String membertv = cursor.getString( cursor.getColumnIndex("member"));

        viewHolder.nametv.setText("name");
        viewHolder.membertv.setText("member");

    }
}
