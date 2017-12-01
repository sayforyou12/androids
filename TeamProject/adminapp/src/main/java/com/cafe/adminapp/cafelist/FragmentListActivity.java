package com.cafe.adminapp.cafelist;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import com.cafe.adminapp.R;

import java.util.List;


public class FragmentListActivity extends AppCompatActivity {

    // private ImageView imgLeft, imgRigth;
    private TextView textname;
    private String [] asc = new String[]{
            "이름순", "별점순", "리뷰순", "즐겨찾기순"};
    private String [] stringasc = new String[]{
            "cafename", "avg_grade", "review_count", "like_count"
    };
    private Integer index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafelist_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("카페路로");

        // TabLayout 초기화
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(Color.parseColor("#bfdfe0"));
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));

        tabLayout.addTab( tabLayout.newTab().setText("카페") );
        tabLayout.addTab( tabLayout.newTab().setText("빙수") );
        tabLayout.addTab( tabLayout.newTab().setText("펫") );

        // ViewPager 초기화
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        // PagerAdater 생성
        ListTabPagerAdapter pagerAdapter = new ListTabPagerAdapter( getSupportFragmentManager(), tabLayout.getTabCount() );

        // PagerAdapter와 ViewPager 연결 : Fragment와 ViewPager 연결
        viewPager.setAdapter( pagerAdapter );

        // 탭 시작지점 정하는부분
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if(type.equals("카페")){
            viewPager.setCurrentItem(0);
        } else if (type.equals("빙수")){
            viewPager.setCurrentItem(1);
        } else{
            viewPager.setCurrentItem(2);
        }

        // ViewPager의 OnPageChangeListener 리스너 설정 : TabLayout과 ViewPager
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ));

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
                viewPager.setCurrentItem( tab.getPosition() );
                int i = viewPager.getCurrentItem();
                if(i==0 || i ==2){
                setValueFragment(stringasc[index]);}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }
    public void imgOnclick(View view){

        textname = (TextView) findViewById(R.id.textname);

        switch (view.getId()){
            case R.id.imgLeft:
                if (index == 0) {
                    index = asc.length - 1;
                    textname.setText(asc[index]);
                } else {
                    index = index - 1;
                    textname.setText(asc[index]);
                }
                setValueFragment(stringasc[index]);
                break;
            case R.id.imgRigth:
                if(index!=asc.length-1){
                    index = index + 1;
                }
                else {
                    index=0;
                }
                textname.setText(asc[index]);
                setValueFragment(stringasc[index]);
                break;
        }
    }

    public void setValueFragment(String orderKind){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){

                if(fragment != null && fragment.isVisible())
                    ((CafeListFragment)fragment).setOrderKind( orderKind );
            }
        }
    }

    public CafeListFragment getVisibleFragment(){
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return (CafeListFragment)fragment;
            }
        }
        return null;
    }
}
