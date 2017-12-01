package com.cafe.adminapp.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cafe.common.Model.ModelCafeinfo;
import com.cafe.adminapp.R;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class CafeList_Adapter extends android.widget.ArrayAdapter<ModelCafeinfo> {

    public ArrayList<ModelCafeinfo> Data = null;

    public CafeList_Adapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelCafeinfo> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    class ViewHolder {
        TextView cafename;
        RatingBar avg_grade;
        TextView review_count;
        TextView like_count;
        ImageView brandImg;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        ModelCafeinfo cafeinfo = getItem(position);

        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.brandImg = (ImageView) itemLayout.findViewById(R.id.brandimage);
            viewHolder.avg_grade = (RatingBar) itemLayout.findViewById(R.id.star);
            viewHolder.cafename = (TextView) itemLayout.findViewById(R.id.cafe_name);
            viewHolder.review_count = (TextView) itemLayout.findViewById(R.id.review_count);
            viewHolder.like_count = (TextView) itemLayout.findViewById(R.id.star_count);
            
            itemLayout.setTag(viewHolder);
        }
        if (cafeinfo.getBrand().toString().equals("이디야")) {
            viewHolder.brandImg.setImageResource(R.drawable.ediya);
        } else if (cafeinfo.getBrand().toString().equals("스타벅스")) {
            viewHolder.brandImg.setImageResource(R.drawable.starbucks);
        } else if (cafeinfo.getBrand().toString().equals("할리스")) {
            viewHolder.brandImg.setImageResource(R.drawable.hallis);
        } else if (cafeinfo.getBrand().toString().equals("밀탑빙수")) {
            viewHolder.brandImg.setImageResource(R.drawable.mealtop);
        } else if (cafeinfo.getBrand().toString().equals("카페베네")) {
            viewHolder.brandImg.setImageResource(R.drawable.cafebene);
        } else if (cafeinfo.getBrand().toString().equals("탐탐")) {
            viewHolder.brandImg.setImageResource(R.drawable.tamtam);
        } else if (cafeinfo.getBrand().toString().equals("커피빈")) {
            viewHolder.brandImg.setImageResource(R.drawable.coffeebean);
        } else if (cafeinfo.getBrand().toString().equals("강아지")) {
            viewHolder.brandImg.setImageResource(R.drawable.dog);
        } else if (cafeinfo.getBrand().toString().equals("고양이")) {
            viewHolder.brandImg.setImageResource(R.drawable.cat);
        } else if (cafeinfo.getBrand().toString().equals("새")) {
            viewHolder.brandImg.setImageResource(R.drawable.bird);
        } else if (cafeinfo.getBrand().toString().equals("개인카페")) {
            viewHolder.brandImg.setImageResource(R.drawable.all_cafe);
        } else if (cafeinfo.getBrand().toString().equals("개인빙수")) {
            viewHolder.brandImg.setImageResource(R.drawable.bingsoo);
        } else if (cafeinfo.getBrand().toString().equals("기타")) {
            viewHolder.brandImg.setImageResource(R.drawable.animal);
        }

        String avg = String.format("%.1f", getItem(position).getAvg_grade());
        float avg2 = Float.parseFloat(avg);
        viewHolder.avg_grade.setRating(avg2);

        viewHolder.cafename.setText(getItem(position).getCafename().toString());
        viewHolder.review_count.setText("리뷰" + getItem(position).getReview_count().toString() + "개");
        viewHolder.like_count.setText("즐겨찾기" + getItem(position).getLike_count() + "명");

        return itemLayout;
        }
}
