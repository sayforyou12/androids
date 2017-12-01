package com.android.girlgroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.girlgroup.model.ModelStudent;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemLayout = convertView;
        ViewHolder viewHolder = null;

        if (itemLayout == null) {
            itemLayout = mLayoutInflater.inflate(R.layout.activity_base_adapter_ex,null);
            // 1.리스트의 한 항목에 해당하는 레이아웃을 생성한다.
            viewHolder = new ViewHolder();
            viewHolder.nametext = (TextView) itemLayout.findViewById(R.id.nametext);
            viewHolder.phonenumber = (TextView) itemLayout.findViewById(R.id.phonenumber);
            viewHolder.number = (TextView) itemLayout.findViewById(R.id.number);

            itemLayout.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) itemLayout.getTag();
        }
        viewHolder.nametext.setText(mData.get(position).getName());
        viewHolder.phonenumber.setText(mData.get(position).getPhonenumber());
        viewHolder.number.setText(mData.get(position).getNumber());

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
