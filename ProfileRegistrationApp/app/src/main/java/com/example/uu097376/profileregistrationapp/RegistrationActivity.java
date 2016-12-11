package com.example.uu097376.profileregistrationapp;

//プロフィール登録フォーム

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    private final String URL = "https://www.google.co.jp/";
    private int[] checked;
    private List<String> hobbyArray;

    EditText firstName;
    EditText lastName;
    RadioButton man;
    RadioButton woman;
    RadioGroup gender;
    EditText tel;
    TextView textHobby;
    Button buttonHobby;
    Spinner job;
    CheckBox checkTerms;
    TextView textTerms;
    Button buttonCheck;


    List<Integer> hobbyList;
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstName = (EditText)findViewById(R.id.Et_First_Name);
        lastName = (EditText)findViewById(R.id.Et_Last_Name);
        man = (RadioButton)findViewById(R.id.Rb_Man);
        woman = (RadioButton)findViewById(R.id.Rb_Woman);
        gender = (RadioGroup)findViewById(R.id.Rg_Gender);
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
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.Bt_ChangeHobby:
                Log.v("確認","ボタン1");
                intent = new Intent(this,HobbyListActivity.class);
                startActivityForResult(intent,0001);
                break;

            case R.id.Bt_check:
                Log.v("確認","ボタン2");
                HumanParcelable human = new HumanParcelable(
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        1,
                        tel.getText().toString(),
                        hobbyArray,
                        job.getSelectedItem().toString()
                );
                intent =new Intent(this,ProfileCheck.class);
                intent.putExtra("profile",human);
                startActivity(intent);
                break;

            case R.id.Cb_Terms:
                if(checkTerms.isChecked() == true ){
                    buttonCheck.setEnabled(true);
                }else{
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
        if(requestCode == 0001){
            System.out.println("戻り値受取");
            if(resultCode == Activity.RESULT_OK){
                System.out.println("戻り値受取");
                System.out.println("test"+intent.getIntArrayExtra("list_boolean")[0]);
                hobbySet(intent);
            }
        }
    }


    private void hobbySet(Intent intent){
        checked = intent.getIntArrayExtra("list_boolean");
        StringBuilder hobbyText = new StringBuilder();
        hobbyArray = new ArrayList<String>();
        for(int i = 0;i<checked.length;i++){
            System.out.println(checked[i]);
            switch (i){
                case 0:
                    if(checked[i] == 1){
                        hobbyText.append("スポーツ");
                        hobbyArray.add("スポーツ");

                    }
                    break;

                case 1:
                    if(checked[i] == 1){
                        hobbyText.append(",映画鑑賞");
                        hobbyArray.add("映画鑑賞");
                    }
                    break;

                case 2:
                    if(checked[i] == 1){
                        hobbyText.append(",旅行");
                        hobbyArray.add("旅行");
                    }
                    break;

                case 3:
                    if(checked[i] == 1){
                        hobbyText.append(",音楽鑑賞");
                        hobbyArray.add("音楽鑑賞");
                    }
                    break;

                case 4:
                    if(checked[i] == 1){
                        hobbyText.append(",料理");
                        hobbyArray.add("料理");
                    }
                    break;

                case 5:
                    if(checked[i] == 1){
                        hobbyText.append(",ゲーム");
                        hobbyArray.add("ゲーム");
                    }
                    break;

                case 6:
                    if(checked[i] == 1){
                        hobbyText.append(",その他");
                        hobbyArray.add("その他");
                    }
                    break;
            }
        }
        System.out.print("hobbySet通過");
        textHobby.setText(hobbyText);
    }
}
