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

    // 趣味マスタの文字列リスト
    private String[] hobbyListArray;

    // 趣味のチェック状態
    private boolean[] hobbyCheckedArray;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_list_activity);

        buttonSet = (Button)findViewById(R.id.Bt_ok) ;
        hobbyList = (ListView)findViewById(R.id.list_hobby);

        // 趣味マスタに値をセットする
        hobbyListArray = getResources().getStringArray(R.array.hobby_values);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_multiple_choice, hobbyListArray);
        hobbyList.setAdapter(adapter);

        // 趣味の選択状態を前画面から取得する
        Intent intent = getIntent();
        hobbyCheckedArray = intent.getBooleanArrayExtra("HOBBY_ITEM_CHECKE_STATE");

        // 画面表示時に前画面から渡ってきたパラメータを元にチェック状態をセットする
        for (int i = 0; i < hobbyCheckedArray.length; i++) {
            if (hobbyCheckedArray[i] == true) {
                hobbyList.setItemChecked(i, true);
            }
        }

        buttonSet.setOnClickListener(this);

        // チェックボックスのクリックイベントを取得してチェックボックスのON、OFF状態を内部の変数に保持する
        hobbyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView selectedHobbyItem = (CheckedTextView) view;

                // チェックボックスのON、OFF状態をチェックが変更されるたびにitemCheckedに設定する
                hobbyCheckedArray[position] = selectedHobbyItem.isChecked();
            }
        });

    }

    //
    public void onClick(View view){
        // 「設定」ボタンタップ時は戻り値としてhobbyCheckedArrayを戻す
        Intent intent = new Intent();
        intent.putExtra("HOBBY_ITEM_CHECKE_STATE", hobbyCheckedArray);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
