package com.cafe.adminapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cafe.adminapp.R;
import com.cafe.adminapp.cafeinfo.Cafeinfo_Review;
import com.cafe.adminapp.cafeinfo.Cafeinfo_Review_Update;
import com.cafe.common.Model.ModelCafeReview;
import com.cafe.common.Model.ModelCafeinfo;

import java.text.SimpleDateFormat;
import java.util.List;

public class CafeReview_Adapter extends ArrayAdapter<ModelCafeReview>{

    private String userid;
    private ModelCafeinfo cafeinfo = new ModelCafeinfo();

    public CafeReview_Adapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<ModelCafeReview> objects, String userid, ModelCafeinfo cafeinfo) {
        super(context, resource, textViewResourceId, objects);
        this.userid = userid;
        this.cafeinfo = cafeinfo;
    }

    class ViewHolder {
        TextView tv_nickname, tv_datetime, tv_review;
        RatingBar rb_review;
        Button btn_review_update, btn_review_delete;

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemLayout = super.getView(position, convertView, parent);
        ViewHolder viewHolder = (ViewHolder) itemLayout.getTag();

        final ModelCafeReview cafeReview = getItem(position);

        if(viewHolder == null){
            viewHolder = new ViewHolder();
            viewHolder.tv_nickname = (TextView) itemLayout.findViewById(R.id.tv_nickname);
            viewHolder.tv_datetime = (TextView) itemLayout.findViewById(R.id.tv_datetime);
            viewHolder.rb_review = (RatingBar) itemLayout.findViewById(R.id.rb_review);
            viewHolder.tv_review = (TextView) itemLayout.findViewById(R.id.tv_review);
            viewHolder.btn_review_update = (Button) itemLayout.findViewById(R.id.btn_review_update);
            viewHolder.btn_review_delete = (Button) itemLayout.findViewById(R.id.btn_review_delete);

            itemLayout.setTag(viewHolder);
        }

        viewHolder.tv_nickname.setText(getItem(position).getUsernickname().toString());

        SimpleDateFormat data= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // E 요일 HH 시간 mm 분 ss 초
        String datetime = data.format(getItem(position).getRegdate().getTime());  // 리뷰 수정 날짜, 시간
        viewHolder.tv_datetime.setText(datetime);

        String avg = String.format("%.1f", getItem(position).getGrade());
        float avg2 = Float.parseFloat(avg);
        viewHolder.rb_review.setRating(avg2);
        viewHolder.tv_review.setText(getItem(position).getContent());

        // 로그인한 아이디와 동일한경우만 수정,삭제버튼 보이기
        if(userid.equals(getItem(position).getUsernickname().toString())){
        }else {
            viewHolder.btn_review_update.setVisibility(View.GONE);
            viewHolder.btn_review_delete.setVisibility(View.GONE);
        }

        return itemLayout;
    }
}
