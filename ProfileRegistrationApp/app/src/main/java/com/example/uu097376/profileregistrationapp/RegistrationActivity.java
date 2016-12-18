package com.example.uu097376.profileregistrationapp;

//プロフィール登録フォーム

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.uu097376.profileregistrationapp.R.id.Tv_Terms;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL = "https://www.google.co.jp/";
    private static final int REQUEST_CODE = 1;
    private static final int SEX_NO_SELECTED = 0;
    private static final int SEX_MAN_SELECTED = 1;
    private static final int SEX_WOMAN_SELECTED = 2;

    // modified by Isobe
    private String[] hobbyArray;
    private boolean[] hobbyChecked;

    EditText firstName;
    EditText lastName;
    RadioButton man;
    RadioButton woman;
    EditText tel;
    TextView textHobby;
    Button buttonHobby;
    Spinner job;
    CheckBox checkTerms;
    TextView textTerms;
    Button buttonCheck;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // 趣味リスト取得
        hobbyArray = getResources().getStringArray(R.array.hobby_values);

        // 趣味のチェック状態を初期化する
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
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onResume(){
        super.onResume();

        buttonHobby.setOnClickListener(this);
        buttonCheck.setOnClickListener(this);
        checkTerms.setOnClickListener(this);
        textTerms.setOnClickListener(this);

        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeNextButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                changeNextButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Bt_ChangeHobby:
                Log.v("確認","ボタン1");
                intent = new Intent(this,HobbyListActivity.class);
                // modified by Isobe
                intent.putExtra("HOBBY_ITEM_CHECKE_STATE", hobbyChecked);
                startActivityForResult(intent, REQUEST_CODE);
                break;

            case R.id.Bt_check:
                Log.v("確認","ボタン2");

                int selectedSex = SEX_NO_SELECTED;
                if (man.isChecked()) {
                    selectedSex = SEX_MAN_SELECTED;
                } else if (woman.isChecked()) {
                    selectedSex = SEX_WOMAN_SELECTED;
                }

                HumanParcelable human = new HumanParcelable(
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        selectedSex,
                        tel.getText().toString(),
                        hobbyChecked,
                        job.getSelectedItemPosition()
                );
                intent =new Intent(this,ProfileCheck.class);
                intent.putExtra("profile",human);
                startActivity(intent);
                break;

            case R.id.Cb_Terms:

                if(checkTerms.isChecked() && !("".equals(firstName.getText().toString())) && !("".equals(lastName.getText().toString()))) {
                    buttonCheck.setEnabled(true);
                }else {
                    buttonCheck.setEnabled(false);
                }
                System.out.println(String.valueOf(checkTerms.isChecked()));
                break;

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
                // modified by Isobe
                // 戻り値のHOBBY_ITEM_CHECKE_STATEを受け取ってhobbyChecked変数にセットする
                hobbyChecked = intent.getBooleanArrayExtra("HOBBY_ITEM_CHECKE_STATE");

                // 趣味の文字列設定
                textHobby.setText(ProfileResistrationAppUtil.getHobbyDispString(hobbyArray, hobbyChecked));
            }
        }
    }

    private void changeNextButtonState() {

        if(checkTerms.isChecked() && !("".equals(firstName.getText().toString())) && !("".equals(lastName.getText().toString()))) {
            buttonCheck.setEnabled(true);
        } else {
            buttonCheck.setEnabled(false);
        }
    }
}