package com.example.yosuke.jsonapp;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by yosuke on 2017/01/29
 * Json取得クラス
 */

public class GetJson extends AsyncTask<String,Void,JSONObject> {
    public GetJson() {
        super();
    }

    @Override
    protected JSONObject doInBackground(String... textUri) {
        HttpURLConnection con = null;
        URL url = null;
        JSONObject json = null;

        try {
            //接続
            url = new URL(textUri[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {128
            //出力
            BufferedInputStream is = new BufferedInputStream(con.getInputStream());
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[];
            int length;
            while ((length = is.read(buffer)) != -1) {
                if (length > 0) {
                    os.write(buffer, 0, length);
                }
            }
            json = new JSONObject(new String(os.toByteArray()));
            return json;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);
        //Jsonパース処理
        try{
            System.out.println("全文「"+json+"」");
            System.out.println("パース１「"+json.getString("request"));
            System.out.println("パース２「"+json.getJSONObject("request").getString("url"));

        }catch (JSONException ex){
            ex.printStackTrace();
        }

    }
}
