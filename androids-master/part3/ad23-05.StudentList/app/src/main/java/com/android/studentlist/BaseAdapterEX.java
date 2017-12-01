package com.android.studentlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseAdapterEX extends BaseAdapter {

    Context mContext = null;
    ArrayList<ModelStudent> mData = null;
    LayoutInflater mLayoutInflater = null;

    public BaseAdapterEX(Context context, ArrayList<ModelStudent> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    class ViewHolder{
        TextView nametext;
        TextView numbertext;
        TextView departmenttext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemLayout = convertView;
        ViewHolder viewHolder = null;

        if (itemLayout == null) {
            itemLayout = mLayoutInflater.inflate(R.layout.activity_base_adapter_ex,null);
            // 1.리스트의 한 항목에 해당하는 레이아웃을 생성한다.
            viewHolder = new ViewHolder();
            viewHolder.nametext = (TextView) itemLayout.findViewById(R.id.nametext);
            viewHolder.numbertext = (TextView) itemLayout.findViewById(R.id.numbertext);
            viewHolder.departmenttext = (TextView) itemLayout.findViewById(R.id.departmenttext);

            itemLayout.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) itemLayout.getTag();
        }
        viewHolder.nametext.setText(mData.get(position).getName());
        viewHolder.numbertext.setText(mData.get(position).getNumber());
        viewHolder.departmenttext.setText(mData.get(position).getDepartment());

        return itemLayout;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void add(int index, ModelStudent addData){
        mData.add(index, addData);
        notifyDataSetChanged();
    }

    public void delete(String index){
        for (int i = 0; i < mData.size(); i++){
            if (index.equals(mData.get(i).getName())){
                mData.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    public void clear(){
        mData.clear();
        notifyDataSetChanged();
    }


}
