package com.example.yosuke.jsonapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//ボタンを押すとログにJsonの中身を表示する

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String URL = "http://animemap.net/api/table/hokkaidou.json";

    Button bt_GetJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_GetJson = (Button)findViewById(R.id.bt_GetJson);
        bt_GetJson.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_GetJson:
                GetJson getJson = new GetJson();
                getJson.execute(URL);
                break;
        }
    }
}
