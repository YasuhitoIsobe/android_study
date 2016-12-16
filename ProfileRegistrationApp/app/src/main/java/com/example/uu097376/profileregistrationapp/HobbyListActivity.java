package com.example.uu097376.profileregistrationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

/**
 * Created by uu097376 on 2016/12/08.
 */

public class HobbyListActivity extends AppCompatActivity implements View.OnClickListener  {
    ListView hobbyList;
    Button buttonSet;

    //Listアイテム
    private String[] item = new String[]{
            "スポーツ",
            "映画鑑賞",
            "旅行",
            "音楽鑑賞",
            "料理",
            "ゲーム",
            "その他"
    };

    // modified by Isobe
    private boolean[] itemChecked;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_list_activity);

        buttonSet = (Button)findViewById(R.id.Bt_ok) ;
        hobbyList = (ListView)findViewById(R.id.list_hobby);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_multiple_choice,item);
        hobbyList.setAdapter(adapter);

        // modified by Isobe
        Intent intent = getIntent();
        itemChecked = intent.getBooleanArrayExtra("HOBBY_ITEM_CHECKE_STATE");

        // 画面表示時に前画面から渡ってきたパラメータを元にチェック状態をセットする
        for (int i = 0; i < itemChecked.length; i++) {
            if (itemChecked[i] == true) {
                hobbyList.setItemChecked(i, true);
            }
        }

        buttonSet.setOnClickListener(this);

        // modified by Isobe
        hobbyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView selectedHobbyItem = (CheckedTextView) view;

                // チェックボックスのON、OFF状態をチェックが変更されるたびにitemCheckedに設定する
                itemChecked[position] = selectedHobbyItem.isChecked();

                // デバッグ出力
                System.out.println("item:" + item[position] + ", checkbox:" + selectedHobbyItem.isChecked());
            }
        });

                                         }

    //
    public void onClick(View view){
        // modified by Isobe
        // 「設定」ボタンタップ時は戻り値としてitemCheckedを戻すだけ

        Intent intent = new Intent();
        intent.putExtra("HOBBY_ITEM_CHECKE_STATE", itemChecked);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
