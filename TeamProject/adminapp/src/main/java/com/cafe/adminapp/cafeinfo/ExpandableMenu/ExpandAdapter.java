package com.cafe.adminapp.cafeinfo.ExpandableMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cafe.adminapp.R;
import com.cafe.common.CommonActvity;
import com.cafe.common.Model.ModelCafeMenu;

import java.util.ArrayList;

public class ExpandAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<GroupData> groupDatas;
    private ArrayList<ArrayList<ModelCafeMenu>> childDatas;
    private LayoutInflater inflater = null;

    public ExpandAdapter() {
    }

    public ExpandAdapter(Context context, ArrayList<GroupData> groupDatas, ArrayList<ArrayList<ModelCafeMenu>> childDatas) {
        this.context = context;
        this.groupDatas = groupDatas;
        this.childDatas = childDatas;
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return groupDatas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childDatas.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupDatas.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childDatas.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View View, ViewGroup parent) {

        if(View == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View = inflater.inflate(R.layout.group_row, null);
        }
        TextView groupName = (TextView)View.findViewById(R.id.groupName);
        String group = String.valueOf(groupDatas.get(groupPosition).getGroupName());
        groupName.setText(group.toString());

        return View;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_row, viewGroup, false );
        }
        TextView menuname = (TextView) view.findViewById(R.id.menuname);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView descrption = (TextView) view.findViewById(R.id.descrption);

        menuname.setText(childDatas.get(i).get(i1).getMenu_name());
        price.setText(childDatas.get(i).get(i1).getPrice().toString()+"원");
        descrption.setText(childDatas.get(i).get(i1).getDescription());

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
