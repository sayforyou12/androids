package com.android.girlgroup;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.girlgroup.model.ModelStudent;

import java.util.ArrayList;

public class ArrayAdapterEX extends ArrayAdapter<ModelStudent> {

    public ArrayAdapterEX(Context context, int res, int textViewResId, ArrayList<ModelStudent> data) {
        super(context, res, textViewResId, data);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if(viewHolder == null){
            viewHolder = new ViewHolder();

            viewHolder.nametext = (TextView) itemLayout.findViewById(R.id.nametext);
            viewHolder.phonenumber = (TextView) itemLayout.findViewById(R.id.phonenumber);
            viewHolder.number = (TextView) itemLayout.findViewById(R.id.number);

            itemLayout.setTag(viewHolder);
        }
        viewHolder.nametext.setText(getItem(position).getName());
        viewHolder.phonenumber.setText(getItem(position).getPhonenumber());
        viewHolder.number.setText(getItem(position).getNumber());
         return itemLayout;
    }

}
