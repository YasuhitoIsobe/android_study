package com.example.yosuke.contentproviderapp;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by yosuke on 2016/11/29.
 * カラムデータ
 *
 */

public class Columns implements BaseColumns {
    // URIパス
    public static final String PATH = "user";
    // コンテントURI
    public static final Uri CONTENT_URI = Uri.parse("content://" + MyContentProvider.AUTHORITY + "/" + PATH);
    // テーブル指定コンテントタイプ
    public static final String CONTENT_TYPE = "vnd.android.cursor.item/vnd.example.users";
    // レコード個別指定コンテントタイプ
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.dir/vnd.example.users";

    // テーブル名
    public static final String TABLE = "human";
    // カラム 名前
    public static final String NAME = "name";
    // カラム メールアドレス
    public static final String ADDRESS = "address";

    // コンストラクタ(呼ばれることは無い)
    private Columns() {}

}
