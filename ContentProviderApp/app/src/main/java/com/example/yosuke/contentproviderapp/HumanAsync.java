package com.example.yosuke.contentproviderapp;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by yosuke on 2016/11/30.
 *
 */

public class HumanAsync extends AsyncQueryHandler {
    public HumanAsync(ContentResolver cr){
        super(cr);
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        Log.v("通過確認:","query");
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                System.out.println(cursor.getColumnName(i) + " : " + cursor.getString(i));
            }
        }
    }

    @Override
    protected void onInsertComplete(int token, Object cookie, Uri uri) {
        Log.v("通過確認:","insert");
    }

    @Override
    protected void onUpdateComplete(int token, Object cookie, int result) {
        Log.v("通過確認:","update");
    }

    @Override
    protected void onDeleteComplete(int token, Object cookie, int result) {
        Log.v("通過確認:","delete");
    }
}
