package com.cafe.common;

import com.cafe.common.Http.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

public class HttpgetBookmark {

    public int getBookmark(int cafeno, int userno){
        String weburl = "http://dbqudtjd1122.cafe24.com/like/getBookmark";

        HttpRequest request = null;
        String response = null;
        int count = -1;

        int httpCode = 0;
        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("cafeno", String.valueOf(cafeno));
            request.addParameter("userno", String.valueOf(userno));

            httpCode = request.post();

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
