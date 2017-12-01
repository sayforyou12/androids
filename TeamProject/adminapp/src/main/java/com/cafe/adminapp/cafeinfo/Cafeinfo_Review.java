package com.cafe.adminapp.cafeinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cafe.adminapp.R;
import com.cafe.common.CommonActvity;
import com.cafe.common.HttpReviewInsert;
import com.cafe.common.Model.ModelCafeReview;
import com.cafe.common.Model.ModelCafeinfo;

public class Cafeinfo_Review extends CommonActvity {

    private RatingBar rb_grade;
    private TextView tv_review_nickname;
    private EditText edit_review;
    private Button btn_review_finsh;
    private ModelCafeinfo cafeinfo;
    private ModelCafeReview cafeReview = new ModelCafeReview();
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafeinfo_review);

        rb_grade = (RatingBar) findViewById(R.id.rb_grade);
        tv_review_nickname = (TextView) findViewById(R.id.tv_review_nickname);
        edit_review = (EditText) findViewById(R.id.edit_review);
        btn_review_finsh = (Button) findViewById(R.id.btn_review_finsh);

        SharedPreferences pref = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        tv_review_nickname.setText(pref.getString("nickname_Set", "").toString());

        intent = getIntent();
        cafeinfo = new ModelCafeinfo();
        cafeinfo = (ModelCafeinfo) intent.getSerializableExtra("cafeinfo");

        btn_review_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cafeReview.setCafeno(cafeinfo.getCafeno());
                cafeReview.setGrade(rb_grade.getRating());
                cafeReview.setUsernickname(tv_review_nickname.getText().toString());
                cafeReview.setContent(edit_review.getText().toString());

                new Cafeinfo_Review.HttpReview().execute(cafeReview);
            }
        });
    }

    // Http List DB 가져오기
    public class HttpReview extends AsyncTask<ModelCafeReview, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(Cafeinfo_Review.this);
            waitDlg.setMessage(" List 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelCafeReview... params) {

            int count = new HttpReviewInsert().reviewinsert(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);

            // Progressbar 감추기 : 서버 요청 완료수 Maiting dialog를 제거한다.
            if (waitDlg != null) {
                waitDlg.dismiss();
                waitDlg = null;
            }
            intent = new Intent(getApplicationContext(), FragmentInfoActivity.class);
            intent.putExtra("reviewcount", s);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
