package com.android.http.paginglistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.mylibrary.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class Http {
    private final String URL_LOGIN         = "http://192.168.0.238:8080/rest/login";
    private final String URL_ARTICLELIST   = "http://192.168.0.238:8080/board/articlelist";
    private final String URL_ARTICLEVIEW   = "http://192.168.0.238:8080/board/articleview";
    private final String URL_ARTICLEWRITE  = "http://192.168.0.238:8080/board/articlewrite";
    private final String URL_ARTICLEMODIFY = "http://192.168.0.238:8080/board/articlemodify";
    private final String URL_ARTICLEDELETE = "http://192.168.0.238:8080/board/articledelete";

    public String login(String id, String pw) throws IOException {
        HttpRequest request = null;
        String response = "";

        try {
            request = new HttpRequest(URL_LOGIN).addHeader("charset", "utf-8");
            request.addParameter("id", id );
            request.addParameter("pw", pw );
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getStringResponse();
            }
            else {
                // error
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return response;
    }
    
    // http://localhost:8080/board/articlelist
    public String articlelist(String id, String pw) {
        HttpRequest request = null;
        String response = "";

        try {
            request = new HttpRequest(URL_ARTICLELIST).addHeader("charset", "utf-8");
            request.addParameter("id", id );
            request.addParameter("pw", pw );
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getStringResponse();
            }
            else {
                // error
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return response;
    }
    
    // http://localhost:8080/board/articleview
    public String articleview(String id, String pw) {
        HttpRequest request = null;
        String response = "";

        try {
            request = new HttpRequest(URL_ARTICLEVIEW).addHeader("charset", "utf-8");
            request.addParameter("id", id );
            request.addParameter("pw", pw );
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getStringResponse();
            }
            else {
                // error
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return response;
    }
    
    // http://localhost:8080/board/articleview
    public String articlewrite(String id, String pw) {
        HttpRequest request = null;
        String response = "";

        try {
            request = new HttpRequest(URL_ARTICLEWRITE).addHeader("charset", "utf-8");
            request.addParameter("id", id );
            request.addParameter("pw", pw );
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getStringResponse();
            }
            else {
                // error
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return response;
    }
    
    // http://localhost:8080/board/articleview
    public String articlemodify(String id, String pw) {

        HttpRequest request = null;
        String response = "";

        try {
            request = new HttpRequest(URL_ARTICLEMODIFY).addHeader("charset", "utf-8");
            request.addParameter("id", id );
            request.addParameter("pw", pw );
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getStringResponse();
            }
            else {
                // error
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return response;
    }
    
    // http://localhost:8080/board/articledelete
    public String articledelete(String id, String pw) {
        HttpRequest request = null;
        String response = "";

        try {
            request = new HttpRequest(URL_ARTICLEDELETE).addHeader("charset", "utf-8");
            request.addParameter("id", id );
            request.addParameter("pw", pw );
            int httpCode = request.post();

            if( httpCode == HttpURLConnection.HTTP_OK ){
                response = request.getStringResponse();
            }
            else {
                // error
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

        return response;
    }

}
