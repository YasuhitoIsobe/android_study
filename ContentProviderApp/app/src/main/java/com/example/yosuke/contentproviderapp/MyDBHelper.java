package com.example.yosuke.contentproviderapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yosuke on 2016/11/29.
 * DBHelper
 */

public class MyDBHelper extends SQLiteOpenHelper {
    // DB名とバージョン
    private static final String DB_NAME = "database";
    private static final int DB_VERSION = 1;

    // コンストラクタ
    public MyDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // データベースのテーブルを作成する
        sqLiteDatabase.execSQL("CREATE TABLE " + Columns.TABLE + "("
        + Columns.NAME + " TEXT,"
        + Columns.ADDRESS + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // DBに変更があったときに呼ばれる
    }
}
