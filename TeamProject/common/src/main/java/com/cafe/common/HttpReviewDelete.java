package com.cafe.common;

import com.cafe.common.Http.HttpRequest;
import com.cafe.common.Model.ModelCafeReview;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

public class HttpReviewDelete {

    public Integer reviewdelete(ModelCafeReview cafeReview) {
        String weburl = "http://dbqudtjd1122.cafe24.com/review/deleteReview";

        HttpRequest request = null;
        String response = null;

        int httpCode = 0;
        try {

            String data = new Gson().toJson(cafeReview);

            request = new HttpRequest(weburl).addHeader("charset", "utf-8")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json");
            httpCode = request.post(data);

            if (httpCode == HttpURLConnection.HTTP_OK) { // HttpURLConnection.HTTP_OK == 200
                response = request.getStringResponse(); // 서버값이 리턴된다
            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }
        return Integer.valueOf(response);
    }
}
