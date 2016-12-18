package com.example.uu097376.profileregistrationapp;

//プロフィール登録フォーム
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import static com.example.uu097376.profileregistrationapp.R.id.Tv_Terms;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String URL = "https://www.google.co.jp/";
    private static final int REQUEST_CODE = 1;
    private static final int SEX_NO_SELECTED = 0;
    private static final int SEX_MAN_SELECTED = 1;
    private static final int SEX_WOMAN_SELECTED = 2;

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

        // テキスト変更イベントリスナークラスを生成
        NameTextWatcher nameTextWatcher = new NameTextWatcher();

        // イベントリスナーと紐付け
        firstName.addTextChangedListener(nameTextWatcher);
        lastName.addTextChangedListener(nameTextWatcher);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Bt_ChangeHobby:
                Intent changeHobbyScreenIntent = new Intent(this,HobbyListActivity.class);
                changeHobbyScreenIntent.putExtra("HOBBY_ITEM_CHECKE_STATE", hobbyChecked);
                startActivityForResult(changeHobbyScreenIntent, REQUEST_CODE);

                break;

            case R.id.Bt_check:
                // 性別の選択状態を取得
                int selectedSex = SEX_NO_SELECTED;
                if (man.isChecked()) {
                    selectedSex = SEX_MAN_SELECTED;
                } else if (woman.isChecked()) {
                    selectedSex = SEX_WOMAN_SELECTED;
                }

                // 次画面に受け渡すパラメータを生成
                HumanParcelable humanParcelable = new HumanParcelable(
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        selectedSex,
                        tel.getText().toString(),
                        hobbyChecked,
                        job.getSelectedItemPosition()
                );

                Intent profileCheckScreenIntent = new Intent(this, ProfileCheck.class);
                profileCheckScreenIntent.putExtra("profile", humanParcelable);
                startActivity(profileCheckScreenIntent);

                break;

            case R.id.Cb_Terms:
                // 次画面遷移ボタンを有効・無効化する判定を行う
                changeNextButtonState();

                break;

            case R.id.Tv_Terms:
                Uri uri = Uri.parse(URL);
                Intent webViewIntent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(webViewIntent);

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent){
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            // 戻り値のHOBBY_ITEM_CHECKE_STATEを受け取ってhobbyChecked変数にセットする
            hobbyChecked = intent.getBooleanArrayExtra("HOBBY_ITEM_CHECKE_STATE");

            // 趣味の文字列設定
            textHobby.setText(ProfileResistrationAppUtil.getHobbyDispString(hobbyArray, hobbyChecked));
        }
    }

    // 次画面遷移可能かどうかを判定してボタンの有効・無効を切り替えるメソッド
    private void changeNextButtonState() {

        // 規約チェック、名前（姓）、名前（名）が全てチェック・入力済の場合のみ次画面遷移用のボタンを有効にする
        if(checkTerms.isChecked() && !("".equals(firstName.getText().toString())) && !("".equals(lastName.getText().toString()))) {
            buttonCheck.setEnabled(true);
        } else {
            buttonCheck.setEnabled(false);
        }
    }

    // テキスト変更イベントを検知するためのリスナークラスを内部クラスとして定義
    class NameTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            RegistrationActivity.this.changeNextButtonState();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}