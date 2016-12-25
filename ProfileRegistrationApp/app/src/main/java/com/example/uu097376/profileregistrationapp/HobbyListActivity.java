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

    private String[] hobbyListArray;
    private boolean[] hobbyCheckedArray;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_list_activity);

        buttonSet = (Button)findViewById(R.id.Bt_ok) ;
        hobbyList = (ListView)findViewById(R.id.list_hobby);

        hobbyListArray = getResources().getStringArray(R.array.hobby_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_multiple_choice,hobbyListArray);
        hobbyList.setAdapter(adapter);

/*        //チェックボックスの真偽値を受け取るには一度操作する必要があるため
        for(int i=0;i < hobbyListArray.length;i++){
            hobbyList.setItemChecked(i,true);
            hobbyList.setItemChecked(i,false);
        }*/

        Intent intent = getIntent();
        hobbyCheckedArray = intent.getBooleanArrayExtra("HOBBY_ITEM_CHECK_STATE");

        for(int i = 0;i < hobbyCheckedArray.length; i++){
            if(hobbyCheckedArray[i] == true){
                hobbyList.setItemChecked(i,true);
            }
        }

        buttonSet.setOnClickListener(this);

        hobbyList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent,View view,int position,long id){
                CheckedTextView selectedHobbyItem = (CheckedTextView)view;

                //
                hobbyCheckedArray[position] = selectedHobbyItem.isChecked();
            }
        }
        );

    }

    //
    public void onClick(View view){
/*        SparseBooleanArray checked = hobbyList.getCheckedItemPositions();
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
        }*/

        Intent intent = new Intent();
        intent.putExtra("HOBBY_ITEM_CHECK_STATE",hobbyCheckedArray);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

}
