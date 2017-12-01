package com.cafe.common;

import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelCafeLike;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;


public class HttpinsertBookmark {

    public int insertBookmark(ModelCafeLike like){
        String weburl = "http://dbqudtjd1122.cafe24.com/like/insertBookmark";

        HttpRequest request = null;
        String response = null;
        int count = -1;

        int httpCode = 0;
        try {
            String data = new Gson().toJson(like);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if(httpCode == HttpURLConnection.HTTP_OK){ // HttpURLConnection.HTTP_OK == 200
                try {
                    response = request.getStringResponse(); // 서버값이 리턴된다
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            request.close();
        }
        count = Integer.valueOf(response);
        return count;
    }
}
