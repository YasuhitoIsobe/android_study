package com.example.yosuke.contentproviderapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("テスト","test");
        HumanController hc = new HumanController(this);
        //hc.StartDelete();

        //
        //hc.StartInsert("田中","東京");
        //hc.StartInsert("荒井","北海道");
        //hc.StartInsert("大久保","広島");

        //引数1に更新する名前、引数2に行進する住所
        // HumanControllerで固定
        hc.StartUpdate("UPDATE_NAME","UPDATE_ADD");

        //全件取り出し
        hc.StartQuery();
        Log.v("テスト","test");


    }
}
