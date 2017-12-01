package com.android.mylibrary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void test_convert_string_to_json() throws Exception {

        //JSONObject 사용법 숙지
        JSONObject json = null;

        String srt = "{'key1':'value1', 'key2':'value2'}";

        json = new JSONObject(srt);

        assertEquals(json.get("key1").toString(),"value1");

    }

    @Test
    public void test_make_json_object(){

        //
        String msg = "";

        // { 'name':'student', 'course':['name','course1',{'information':'test', 'id':3}]}


        try {
            JSONObject json = new JSONObject();
            json.put("name", "student");

            // {'information':'test', 'id':3} 만들기
            JSONObject item = new JSONObject();
            item.put("information","");
            item.put("id"         , 3);



            JSONArray jarray = new JSONArray();
            jarray.put(item);
            jarray.put("name");
            jarray.put("course1");

            json.put("course", jarray);

            msg = json.toString();

            assertEquals(json.get("name").toString(),"student");
            JSONArray  test1 = (JSONArray) json.get("course");
            JSONObject test2 = (JSONObject) test1.get(2);

            assertEquals(test2.get("information").toString(),"test");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public  void test_get_currentversion(){
        String      weburl = "http://192.168.0.78:8080/rest/currentversion";
        HttpRequest request = null;
        String      response = "";

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            int httpCode = request.get();

            if (HttpURLConnection.HTTP_OK == httpCode) { //HttpURLConnection.HTTP_OK  == 200
                response = request.getStringResponse(); // 서버 값이 리턴된다.
            }

            else{
                //error
            }

            assertNotEquals(response, "1");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.close();
        }


    }
    @Test
    public void test_get_login_success(){
        String weburl = "http://192.168.0.78:8080/rest/login";
        HttpRequest request = null;
        String      response = "";

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("id", "id1");
            request.addParameter("pw", "pw1");
            int httpCode = request.post();
            if (httpCode == HttpURLConnection.HTTP_OK){
                response = request.getStringResponse();
            }
            else {
                //error
            }
            assertNotEquals(response, "1");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            request.close();
        }

    }

    @Test
    public void test_get_json_object(){
        String weburl = "http://192.168.0.78:8080/rest/personone";
        HttpRequest request = null;
        JSONObject response = null;

        try {
            request = new HttpRequest(weburl).addHeader("charset", "utf-8");
            request.addParameter("name", "id1");
            int httpCode = request.get();
            if (httpCode == HttpURLConnection.HTTP_OK){
                response = request.getJSONObjectResponse();
            }
            else {
                //error
            }

            assertEquals("valid"   ,response.getString("id"));
            assertEquals("valpw"   ,response.getString("pw"));
            assertEquals("valname" ,response.getString("name"));
            assertEquals("valemail",response.getString("email"));

            //json을 modelperson으로 변경
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            request.close();
        }

    }

}