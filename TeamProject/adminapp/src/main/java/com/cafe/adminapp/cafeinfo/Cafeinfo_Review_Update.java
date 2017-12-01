package com.cafe.adminapp.cafeinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cafe.adminapp.R;
import com.cafe.common.HttpReviewUpdate;
import com.cafe.common.Model.ModelCafeReview;
import com.cafe.common.Model.ModelCafeinfo;

public class Cafeinfo_Review_Update extends AppCompatActivity {

    private RatingBar rb_upgrade;
    private TextView tv_review_upnickname;
    private EditText edit_upreview;
    private Button btn_upreview_finsh;
    private ModelCafeinfo cafeinfo = new ModelCafeinfo();
    private ModelCafeReview cafeReview = new ModelCafeReview();
    private Intent intent;
    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafeinfo_review_update);

        rb_upgrade = (RatingBar) findViewById(R.id.rb_upgrade);
        tv_review_upnickname = (TextView) findViewById(R.id.tv_review_upnickname);
        edit_upreview = (EditText) findViewById(R.id.edit_upreview);
        btn_upreview_finsh = (Button) findViewById(R.id.btn_upreview_finsh);

        intent = getIntent();
        cafeinfo = (ModelCafeinfo) intent.getSerializableExtra("cafeinfo");
        cafeReview = (ModelCafeReview) intent.getSerializableExtra("cafereview");

        String avg = String.format("%.1f", cafeReview.getGrade());
        float avg2 = Float.parseFloat(avg);
        rb_upgrade.setRating(avg2);
        tv_review_upnickname.setText(cafeReview.getUsernickname().toString());
        edit_upreview.setText(cafeReview.getContent().toString());

        btn_upreview_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cafeReview.setCafeno(cafeinfo.getCafeno());
                cafeReview.setGrade(rb_upgrade.getRating());
                cafeReview.setUsernickname(tv_review_upnickname.getText().toString());
                cafeReview.setContent(edit_upreview.getText().toString());

                new HttpUpdate().execute(cafeReview);
            }
        });
    }

    // Http List DB 가져오기
    public class HttpUpdate extends AsyncTask<ModelCafeReview, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // ProgressDialog 보이기
            // 서버 요청 완료후 Mating dialog를 보여주도록 한다.
            waitDlg = new ProgressDialog(Cafeinfo_Review_Update.this);
            waitDlg.setMessage(" List 불러오는 중");
            waitDlg.show();
        }

        @Override
        protected Integer doInBackground(ModelCafeReview... params) {

            int count = new HttpReviewUpdate().reviewupdate(params[0]);

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
