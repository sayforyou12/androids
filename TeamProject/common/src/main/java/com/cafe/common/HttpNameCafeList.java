package com.cafe.common;


import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelCafeinfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class HttpNameCafeList {

    public List<ModelCafeinfo> name(String name){
        String weburl = "http://dbqudtjd1122.cafe24.com/team/getCafeListName";

        HttpRequest request = null;
        JSONArray response = null;
        List<ModelCafeinfo> ModelCafeinfo = null;

        int httpCode = 0;
        try {

            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("cafename", name);

            httpCode = request.post();

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getJSONArrayResponse(); // 서버값이 리턴된다
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
            }

            String jsonInString = response.toString();
            ModelCafeinfo = new Gson().fromJson(jsonInString, new TypeToken<List<ModelCafeinfo>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return ModelCafeinfo;
    }
}
