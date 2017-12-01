package com.cafe.adminapp.cafeinfo;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cafe.adminapp.R;
import com.cafe.common.CommonActvity;
import com.cafe.common.HttpCafeList;
import com.cafe.common.HttpDeleteBookmark;
import com.cafe.common.HttpgetBookmark;
import com.cafe.common.HttpinsertBookmark;
import com.cafe.common.Model.ModelCafeLike;
import com.cafe.common.Model.ModelCafeinfo;


import java.util.List;


public class FragmentInfoActivity extends CommonActvity {

    public ModelCafeinfo cafeinfo = new ModelCafeinfo();
    private TextView tv_avg_grade, tv_review_count, tv_like_count;
    private RatingBar rb_avg_grade;
    public String strnickname;
    public Integer userno, likecount = -1;
    public Integer REQUEST_CODE = 8573;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafeinfo_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        cafeinfo = new ModelCafeinfo();
        cafeinfo = (ModelCafeinfo) intent.getSerializableExtra("cafeinfo");

        SharedPreferences pref = getSharedPreferences("Setting", Context.MODE_PRIVATE);
        strnickname = pref.getString("nickname_Set", "");
        userno = pref.getInt("userno_Set", -1);

        // TabLayout 초기화
        TabLayout tabLayout = (TabLayout) findViewById(R.id.cafeinfotab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#bfdfe0"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));

        tabLayout.addTab(tabLayout.newTab().setText("메뉴"));
        tabLayout.addTab(tabLayout.newTab().setText("정보"));
        tabLayout.addTab(tabLayout.newTab().setText("리뷰"));

        setTitle(cafeinfo.getCafename().toString());

        tv_avg_grade = (TextView) findViewById(R.id.tv_avg_grade);
        tv_review_count = (TextView) findViewById(R.id.tv_review_count);
        tv_like_count = (TextView) findViewById(R.id.tv_like_count);
        rb_avg_grade = (RatingBar) findViewById(R.id.rb_avg_grade);

        String avg = String.format("%.1f", cafeinfo.getAvg_grade());
        float avg2 = Float.parseFloat(avg);
        tv_avg_grade.setText(avg);
        tv_review_count.setText(cafeinfo.getReview_count().toString());
        tv_like_count.setText(cafeinfo.getLike_count().toString());
        rb_avg_grade.setRating(avg2);

        final Fragment fragmentManager = getSupportFragmentManager().findFragmentById(R.id.expanded_menu);

        // ViewPager 초기화
        final ViewPager viewPager = (ViewPager) findViewById(R.id.cafeinfo_view_pager);

        // PagerAdater 생성
        InfoTabPagerAdapter pagerAdapter = new InfoTabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        // PagerAdapter와 ViewPager 연결 : Fragment와 ViewPager 연결
        viewPager.setAdapter(pagerAdapter);

        // ViewPager의 OnPageChangeListener 리스너 설정 : TabLayout과 ViewPager
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Tab을 양쪽 2칸까지 정보를 유지한다.
        viewPager.setOffscreenPageLimit(2);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // TabSelectedListener 설정 : 화면에서 tab을 클릭할때
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        if(userno == null || userno ==-1){

        }else {
            new getBookmark().execute(cafeinfo.getCafeno(), userno);
        }
    }

    // 타이틀바 이미지버튼(액션바) 만들기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(true);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.

        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.title_bar, null);
        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        final ImageButton like = (ImageButton) findViewById(R.id.like);
        if(likecount == 1){
            like.setImageResource(R.drawable.like);
        } else if(likecount == null || likecount == -1){
        }

        final ModelCafeLike ModelLike = new ModelCafeLike();
        ModelLike.setCafeno(cafeinfo.getCafeno());
        ModelLike.setUserno(userno);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userno == null || userno ==-1){
                    Toast.makeText(FragmentInfoActivity.this, "로그인 해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    if (likecount == 1) {
                        like.setImageResource(R.drawable.not_like);
                        new deleteBookmark().execute(ModelLike);
                    } else {
                        like.setImageResource(R.drawable.like);
                        new insertBookmark().execute(ModelLike);
                    }
                }
            }
        });

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Integer reviewcount = data.getIntExtra("reviewcount", -1);

                setValueFragment(cafeinfo.getCafeno());
            }
            //리턴값이 없을때
            else {
            }
        }
    }

    public void setValueFragment(Integer cafeno){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){

                if(fragment != null && fragment.isVisible())
                    ((CafeinfoFragment)fragment).setCafeno( cafeno );
            }
        }
    }

    // Http Likecount 가져오기(즐겨찾기 확인)
    public class getBookmark extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int count = new HttpgetBookmark().getBookmark(params[0] , params[1]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            likecount = s;
        }
    }

    // Http Likec 삭제(즐겨찾기 삭제)
    public class deleteBookmark extends AsyncTask<ModelCafeLike, Integer, Integer> {

        private ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(ModelCafeLike... params) {
            int count = new HttpDeleteBookmark().deleteBookmark(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            likecount = -1;
        }
    }

    // Http Like 추가(즐겨찾기 추가)
    public class insertBookmark extends AsyncTask<ModelCafeLike, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(ModelCafeLike... params) {
            int count = new HttpinsertBookmark().insertBookmark(params[0]);

            return count;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            likecount = 1;
        }
    }
}
