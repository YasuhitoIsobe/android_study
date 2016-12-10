package com.example.yosuke.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by yosuke on 2016/11/28.
 */

public class MyService extends Service {

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        System.out.println("処理を実行しました");

        return super.onStartCommand(intent,flags,startId);
    }


}
