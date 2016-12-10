package com.example.yosuke.contentproviderapp;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by yosuke on 2016/11/30.
 * DAO
 */

public class HumanController  {
    Context context;
    HumanAsync ha;


    public HumanController(Context context){
        this.context = context;
    }

    //登録処理 第1：名前 第2：住所
    public void StartInsert(String name,String add){
        ha = new HumanAsync(context.getContentResolver());
        ContentValues cv = new ContentValues();
        cv.put(Columns.NAME,name);
        cv.put(Columns.ADDRESS,add);
        ha.startInsert(0,null,Columns.CONTENT_URI,cv);
    }
    public void StartQuery(){
        ha = new HumanAsync(context.getContentResolver());
        System.out.print("");
        //条件を指定する場合は第5引数にWhereを入れる
        ha.startQuery(0,null,Columns.CONTENT_URI,null,null,null,null);
        System.out.print("");

    }
    public void StartUpdate(String name,String add){
        ha = new HumanAsync(context.getContentResolver());
        ContentValues cv = new ContentValues();
        cv.put(Columns.NAME,name);
        cv.put(Columns.ADDRESS,add);
        ha.startUpdate(0,null,Columns.CONTENT_URI,cv,"name = '田中'" ,null);

    }
    //全件削除
    public void StartDelete(){
        ha = new HumanAsync(context.getContentResolver());
        ha.startDelete(0,null,Columns.CONTENT_URI,null,null);

    }
}
