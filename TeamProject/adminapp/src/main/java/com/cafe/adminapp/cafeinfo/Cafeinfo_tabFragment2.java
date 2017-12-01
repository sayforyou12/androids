package com.cafe.adminapp.cafeinfo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cafe.adminapp.R;
import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelCafeinfo;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Cafeinfo_tabFragment2 extends CafeinfoFragment {

    private View view = null;
    private ModelCafeinfo cafeinfo = new ModelCafeinfo();
    private TextView tv_bookmark_count,tv_cafe_name,tv_cafe_brand,tv_cafe_opentime,tv_cafe_delivery,tv_cafe_addr,tv_cafe_businessnum,tv_cafe_phone;
    private String cafeno = "";

    public Cafeinfo_tabFragment2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cafeinfo_tab_fragment_2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        cafeinfo = ((FragmentInfoActivity) getActivity()).cafeinfo;
        cafeno = cafeinfo.getCafeno().toString();

        tv_bookmark_count = (TextView) view.findViewById(R.id.tv_bookmark_count);
        tv_cafe_name = (TextView) view.findViewById(R.id.tv_cafe_name);
        tv_cafe_brand = (TextView) view.findViewById(R.id.tv_cafe_brand);
        tv_cafe_addr = (TextView) view.findViewById(R.id.tv_cafe_addr);
        tv_cafe_businessnum = (TextView) view.findViewById(R.id.tv_cafe_businessnum);
        tv_cafe_opentime = (TextView) view.findViewById(R.id.tv_cafe_opentime);
        tv_cafe_delivery = (TextView) view.findViewById(R.id.tv_cafe_delivery);
        tv_cafe_phone = (TextView) view.findViewById(R.id.tv_cafe_phone);

        new HttpCafeinfofrg2().execute(cafeno);

    }

    public class HttpCafeinfofrg2 extends AsyncTask<String, Integer, ModelCafeinfo> {// 첫 param 인자 = ID, PW 가운데 인자는 현재 진행률 = Integer 결과 Id,PW String

        ProgressDialog waitDlg = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected ModelCafeinfo doInBackground(String... params) {

            String cafeno = params[0];

            ModelCafeinfo result = getcafeone(cafeno);

            return result;
        }

        @Override
        protected void onPostExecute(ModelCafeinfo cafeinfo) {
            super.onPostExecute(cafeinfo);


            // 받은 결과 출력
            //success
            tv_bookmark_count.setText("즐겨 찾기 : "+cafeinfo.getLike_count().toString());
            tv_cafe_name.setText("카페명 : "+cafeinfo.getCafename().toString());
            tv_cafe_phone.setText("매장 번호 : "+cafeinfo.getCafephone().toString());
            tv_cafe_brand.setText(cafeinfo.getBrand().toString());
            tv_cafe_addr.setText("매장 주소 : "+cafeinfo.getCafeaddr().toString());
            tv_cafe_businessnum.setText("사업자 번호 : "+cafeinfo.getBusinessnum().toString());
            tv_cafe_opentime.setText("영업 시간 : "+cafeinfo.getOpentime().toString());
            tv_cafe_delivery.setText("배달 가능 지역 : "+cafeinfo.getDeliveryloc().toString());
        }
    }

    public ModelCafeinfo getcafeone(String cafeno){
        String weburl = "http://dbqudtjd1122.cafe24.com/team/getCafeOne";

        HttpRequest request = null;
        JSONObject response = null;
        ModelCafeinfo cafeinfo = new ModelCafeinfo();

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("cafeno", cafeno);
            int httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK){
                response = request.getJSONObjectResponse();

            }else{
                //error
            }
            String jsonInString = response.toString();
            cafeinfo = new Gson().fromJson(jsonInString, ModelCafeinfo.class);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return cafeinfo;
    }


}
