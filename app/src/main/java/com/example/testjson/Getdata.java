package com.example.testjson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Getdata {
    static JSONArray Data = new JSONArray();


    public static JSONArray get(String account,String password) throws IOException, JSONException {
        // 用網址方式來傳帳密
        URL url = new URL("URL"+"?a="+account +"&p="+password);
        // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 建立 Google 比較挺的 HttpURLConnection 物件
        connection.setRequestMethod("POST");
        // 設定連線方式為 POST
        connection.setDoOutput(true); // 允許輸出
        connection.setDoInput(true); // 允許讀入
        connection.setUseCaches(false); // 不使用快取
        connection.connect(); // 開始連線
        /*
        DataOutputStream outputStream = new DataOutputStream(connection
                .getOutputStream());
        String content = "a=" + password ;
        outputStream.writeBytes(content);
        outputStream.flush();
        outputStream.close();

         */

        int responseCode =
                connection.getResponseCode();
        // 建立取得回應的物件
        if (responseCode ==
                HttpURLConnection.HTTP_OK) {
            // 如果 HTTP 回傳狀態是 OK ，而不是 Error
            InputStream inputStream =
                    connection.getInputStream();
            // 取得輸入串流(x)
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            // 讀取輸入串流的資料
            String box = ""; // 宣告存放用字串\
            StringBuilder builder = new StringBuilder();
            //JSONArray A = new JSONArray();
            String line = null; // 宣告讀取用的字串
            //int count = 0;
            //把資料庫數值傳回來，並用JsonArray 來做儲存
            while ((line = bufReader.readLine()) != null) {
                Data = new JSONArray(line);
            }
            // 關閉輸入串流
            inputStream.close();
        }
        return Data;
    }

    /*
    public static JSONArray getstu(String A) throws IOException, JSONException {
        URL url = new URL("http://192.168.121.23/android_test/getdata.php?id=1");
        // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 建立 Google 比較挺的 HttpURLConnection 物件
        connection.setRequestMethod("POST");
        // 設定連線方式為 POST
        connection.setDoOutput(true); // 允許輸出
        connection.setDoInput(true); // 允許讀入
        connection.setUseCaches(false); // 不使用快取
        connection.connect(); // 開始連線
        int responseCode =
                connection.getResponseCode();
        // 建立取得回應的物件
        if (responseCode ==
                HttpURLConnection.HTTP_OK) {
            // 如果 HTTP 回傳狀態是 OK ，而不是 Error
            InputStream inputStream =
                    connection.getInputStream();
            // 取得輸入串流(x)
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            // 讀取輸入串流的資料
            String box = ""; // 宣告存放用字串\
            StringBuilder builder = new StringBuilder();
            //JSONArray A = new JSONArray();
            String line = null; // 宣告讀取用的字串
            int count = 0;
            while ((line = bufReader.readLine()) != null) {
                Data = new JSONArray(line);
            }
            // 關閉輸入串流
            inputStream.close();
        }
        return Data;
    }

     */
}
