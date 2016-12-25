package com.example.uu097376.profileregistrationapp;

//プロフィール登録フォーム

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.uu097376.profileregistrationapp.R.id.Tv_Terms;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL = "https://www.google.co.jp/";
    private static final int REQUEST_CODE = 1;
    private static final int GENDER_NO_SELECTED = 0;
    private static final int GENDER_MAN_SELECTED = 1;
    private static final int GENDER_WOMAN_SELECTED = 2;
    private static final String DIVIDER_STRING =",";

    private String[] hobbyArray;
    private boolean[] hobbyChecked;

    private EditText firstName;
    private EditText lastName;
    private RadioButton man;
    private RadioButton woman;
    private EditText tel;
    private TextView textHobby;
    private Button buttonHobby;
    private Spinner job;
    private CheckBox checkTerms;
    private TextView textTerms;
    private Button buttonCheck;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        hobbyArray = getResources().getStringArray(R.array.hobby_list);
        hobbyChecked = new boolean[hobbyArray.length];

        firstName = (EditText)findViewById(R.id.Et_First_Name);
        lastName = (EditText)findViewById(R.id.Et_Last_Name);
        man = (RadioButton)findViewById(R.id.Rb_Man);
        woman = (RadioButton)findViewById(R.id.Rb_Woman);
        tel = (EditText)findViewById(R.id.Et_Tel);
        textHobby = (TextView)findViewById(R.id.Tv_Hobby);
        buttonHobby = (Button)findViewById(R.id.Bt_ChangeHobby);
        job = (Spinner) findViewById(R.id.Spi_Job);
        checkTerms = (CheckBox)findViewById(R.id.Cb_Terms);
        textTerms = (TextView)findViewById(Tv_Terms);
        buttonCheck = (Button)findViewById(R.id.Bt_check);
        buttonCheck.setEnabled(false);
    }

    @Override
    protected void onResume(){
        super.onResume();
        buttonHobby.setOnClickListener(this);
        buttonCheck.setOnClickListener(this);
        checkTerms.setOnClickListener(this);
        textTerms.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            //趣味変更ボタンクリック処理
            case R.id.Bt_ChangeHobby:
                intent = new Intent(this,HobbyListActivity.class);
                intent.putExtra("HOBBY_ITEM_CHECK_STATE",hobbyChecked);
                startActivityForResult(intent,REQUEST_CODE);
                break;

            //入力完了ボタンクリック処理
            case R.id.Bt_check:
                /*//チェックされている性別ラジオボタンのIDを取得
                int rgId = gender.getCheckedRadioButtonId();
                //性別の状態確認
                if((RadioButton) findViewById(rgId) == null){
                    genderString = "";
                }else {
                    genderString = ((RadioButton) findViewById(rgId)).getText().toString();
                }*/
                int selectedSex = GENDER_NO_SELECTED;
                if(man.isChecked()){
                    selectedSex = GENDER_MAN_SELECTED;
                }else if (woman.isChecked()){
                    selectedSex = GENDER_WOMAN_SELECTED;
                }

                //氏名必須チェック
                if(firstName.getText().toString().equals("") || lastName.getText().toString().equals("")){
                    Toast.makeText(this,"姓名が未入力です",Toast.LENGTH_SHORT).show();
                }else {
                    HumanParcelable human = new HumanParcelable(
                            firstName.getText().toString(),
                            lastName.getText().toString(),
                            selectedSex,
                            tel.getText().toString(),
                            hobbyChecked,
                            job.getSelectedItem().toString()
                    );
                    //確認画面遷移処理
                    intent = new Intent(this, ProfileCheck.class);
                    intent.putExtra("profile", human);
                    startActivity(intent);
                }
                break;
            //利用規約チェックボックスクリック処理
            case R.id.Cb_Terms:
                if(checkTerms.isChecked() == true ){
                    buttonCheck.setEnabled(true);
                }else{
                    buttonCheck.setEnabled(false);
                }
                break;

            //テキスト「利用規約」クリック時処理
            case R.id.Tv_Terms:
                Uri uri = Uri.parse(URL);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        System.out.println("戻り値受取");
        if(requestCode == REQUEST_CODE){
            System.out.println("戻り値受取");
            if(resultCode == Activity.RESULT_OK){
                System.out.println("戻り値受取");
                hobbyChecked = intent.getBooleanArrayExtra("HOBBY_ITEM_CHECK_STATE");
                textHobby.setText(HobbyStringSet.hobbySet(intent.getBooleanArrayExtra("HOBBY_ITEM_CHECK_STATE"),hobbyArray));
            }
        }
    }

    public StringBuilder hobbySet(Intent intent){
        StringBuilder hobbyDispString = new StringBuilder();
        hobbyChecked = intent.getBooleanArrayExtra("HOBBY_ITEM_CHECK_STATE");

        for(int i = 0;i < hobbyChecked.length;i++) {
            if (hobbyChecked[i] == true) {
                if (!("".equals(hobbyDispString.toString()))) {
                    hobbyDispString.append(DIVIDER_STRING);
                }
                hobbyDispString.append(hobbyArray[i]);
            }
        }
        System.out.print("hobbySet通過");
        return hobbyDispString;
/*        //boolean[] checked = intent.getBooleanArrayExtra("list_boolean");
        checked = intent.getIntArrayExtra("list_boolean");
        StringBuilder hobbyText = new StringBuilder();
        hobbyArray = new ArrayList<String>();
        int hobbyFlag = 0;
        for(int i = 0;i<checked.length;i++){
            System.out.println(checked[i]);

            if(checked[i] == 1){
                if(hobbyFlag == 1){
                    hobbyText.append(",");
                }
            }
            switch (i){
                case 0:
                    if(checked[i] == 1){
                        hobbyText.append("スポーツ");
                        hobbyArray.add("スポーツ");
                        hobbyFlag = 1;
                    }
                    break;

                case 1:
                    if(checked[i] == 1){
                        hobbyText.append("映画鑑賞");
                        hobbyArray.add("映画鑑賞");
                        hobbyFlag = 1;
                    }
                    break;

                case 2:
                    if(checked[i] == 1){
                        hobbyText.append("旅行");
                        hobbyArray.add("旅行");
                        hobbyFlag = 1;
                    }
                    break;

                case 3:
                    if(checked[i] == 1){
                        hobbyText.append("音楽鑑賞");
                        hobbyArray.add("音楽鑑賞");
                        hobbyFlag = 1;
                    }
                    break;

                case 4:
                    if(checked[i] == 1){
                        hobbyText.append("料理");
                        hobbyArray.add("料理");
                        hobbyFlag = 1;
                    }
                    break;

                case 5:
                    if(checked[i] == 1){
                        hobbyText.append("ゲーム");
                        hobbyArray.add("ゲーム");
                        hobbyFlag = 1;
                    }
                    break;

                case 6:
                    if(checked[i] == 1){
                        hobbyText.append("その他");
                        hobbyArray.add("その他");
                        hobbyFlag = 1;
                    }
                    break;
            }*/
    }
}
