package com.android.studentlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArrayAdapterEX extends ArrayAdapter<ModelStudent> {

    public ArrayAdapterEX(Context context, int res, int textViewResId, List<ModelStudent> data) {
        super(context, res, textViewResId, data);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        if (viewHolder == null) {
            viewHolder = new ViewHolder();

            viewHolder.nametext       = (TextView) itemLayout.findViewById(R.id.nametext);
            viewHolder.numbertext     = (TextView) itemLayout.findViewById(R.id.numbertext);
            viewHolder.departmenttext = (TextView) itemLayout.findViewById(R.id.departmenttext);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.nametext.setText(getItem(position).getName());
        viewHolder.numbertext.setText(getItem(position).getNumber());
        viewHolder.departmenttext.setText(getItem(position).getDepartment());

        return itemLayout;
    }


    class ViewHolder {
        TextView nametext;
        TextView numbertext;
        TextView departmenttext;
    }

}
