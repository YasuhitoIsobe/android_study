package com.example.yosuke.contentproviderapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by yosuke on 2016/11/29.
 * ContentProviderクラス
 */

public class MyContentProvider extends ContentProvider{
    // Authority
    public static final String AUTHORITY = "com.example";

    // USERS テーブル URI ID
    private static final int USERS = 1;
    // USERS テーブル 個別 URI ID
    private static final int USER_ID = 2;

    // 利用者がメソッドを呼び出したURIに対応する処理を判定処理に使用します
    private static UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, Columns.PATH, USERS);
        sUriMatcher.addURI(AUTHORITY, Columns.PATH + "/#", USER_ID);
    }

    // DBHelperのインスタンス
    private MyDBHelper mDBHelper;

    // コンテンツプロバイダの作成
    @Override
    public boolean onCreate() {
        mDBHelper = new MyDBHelper(getContext());
        return true;
    }

    // query実行
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (sUriMatcher.match(uri)) {
            case USERS:
            case USER_ID:
                queryBuilder.setTables(Columns.TABLE);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    // insert実行
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String insertTable;
        Uri contentUri;
        switch (sUriMatcher.match(uri)) {
            case USERS:
                insertTable = Columns.TABLE;
                contentUri = Columns.CONTENT_URI;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        long rowId = db.insert(insertTable, null, values);
        if (rowId > 0) {
            Uri returnUri = ContentUris.withAppendedId(contentUri, rowId);
            getContext().getContentResolver().notifyChange(returnUri, null);
            return returnUri;
        } else {
            throw new IllegalArgumentException("Failed to insert row into " + uri);
        }
    }

    // update実行
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int count;
        //String id = uri.getPathSegments().get(0);
        count = db.update(Columns.TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    // delete実行
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
            case USERS:
            case USER_ID:
                count = db.delete(Columns.TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    // コンテントタイプ取得
    @Override
    public String getType(Uri uri) {
        switch(sUriMatcher.match(uri)) {
            case USERS:
                return Columns.CONTENT_TYPE;
            case USER_ID:
                return Columns.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }
}
