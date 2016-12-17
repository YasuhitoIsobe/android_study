package com.example.uu097376.profileregistrationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_list_activity);

        buttonSet = (Button)findViewById(R.id.Bt_ok) ;
        hobbyList = (ListView)findViewById(R.id.list_hobby);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_multiple_choice,item);
        hobbyList.setAdapter(adapter);

        //チェックボックスの真偽値を受け取るには一度操作する必要があるため
        for(int i=0;i < item.length;i++){
            hobbyList.setItemChecked(i,true);
            hobbyList.setItemChecked(i,false);
        }

        buttonSet.setOnClickListener(this);

    }

    //
    public void onClick(View view){
        SparseBooleanArray checked = hobbyList.getCheckedItemPositions();
        System.out.println("確認です"+checked);
        Boolean[] checked3 = new Boolean[checked.size()];
        int[] checked2 = new int[checked.size()];
        for(int i = 0;i < checked.size();i++){
            checked3[i] = checked.get(i);
            if(checked.get(i) == true){
                checked2[i] = 1;
            }else {
                checked2[i] = 0;
            }
        }

        Intent intent = new Intent();
        intent.putExtra("list_boolean",checked3);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

}
