package com.cafe.common;

import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelCafeMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class HttpCafeMenuList {

    public List<String> Menulist(String brand) {
        String weburl = "http://dbqudtjd1122.cafe24.com/menu/getCafecd";

        HttpRequest request = null;
        JSONArray response = null;
        List<String> menuce = null;

        int httpCode = 0;
        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("brand", brand);


            httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }

            // JSONArray를 List<ModelMenu> 객체로 변환
            String jsonInString = response.toString();
            menuce = new Gson().fromJson(jsonInString, new TypeToken<List<String>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return menuce;
    }

    public List<ModelCafeMenu> Menulist2(String menucd, String brand) {
        String weburl = "http://dbqudtjd1122.cafe24.com/menu/getCafeMenu";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCafeMenu> menuList = new ArrayList<ModelCafeMenu>();
        ModelCafeMenu menu = new ModelCafeMenu();

        int httpCode = 0;

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("menucd", menucd);
            request.addParameter("brand", brand);

            httpCode = request.post();

            if (httpCode == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }
            // JSONArray를 List<ModelMenu> 객체로 변환
            String jsonInString = response.toString();
            menuList = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCafeMenu>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return menuList;
    }
}
