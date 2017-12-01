package com.cafe.common;

import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelCafeinfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpCafeList {


    public List<ModelCafeinfo> itemlist(ModelCafeinfo obj, String orderKind) {
        String weburl = "http://dbqudtjd1122.cafe24.com/team/getcafelist";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCafeinfo> list = null;

        //String asc = fragmentListActivity.result();

        int httpCode = 0;
        try {

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("cafebigtype", obj);
            map.put("orderKind", orderKind);

            // ModelPerson을 json으로 변환
            String data = new Gson().toJson(map);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");

            httpCode = request.post(data);

            if (httpCode == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
            }

            // JSONObject json = (JSONObject) response.get(1); 확인

            // JSONArray를 List<ModelCafe> 객체로 변환
            String jsonInString = response.toString();
            list = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCafeinfo>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return list;
    }


}
