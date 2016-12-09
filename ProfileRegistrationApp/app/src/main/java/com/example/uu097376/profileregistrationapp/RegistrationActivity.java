package com.example.uu097376.profileregistrationapp;

//プロフィール登録フォーム

import android.content.Intent;
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

import static com.example.uu097376.profileregistrationapp.R.id.TextTerms;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etFname;
    EditText etLname;
    RadioButton radioMan;
    RadioButton radioWoman;
    RadioGroup radioSei;
    EditText etTel;
    TextView textHobby;
    Button buttonHobby;
    Spinner spJob;
    CheckBox checkTerms;
    TextView textTerms;
    Button sendCheck;

    List<String> testArray;
    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFname = (EditText)findViewById(R.id.Fname);
        etLname = (EditText)findViewById(R.id.Lname);
        radioMan = (RadioButton)findViewById(R.id.Man);
        radioWoman = (RadioButton)findViewById(R.id.Woman);
        radioSei = (RadioGroup)findViewById(R.id.sei);
        etTel = (EditText)findViewById(R.id.Tel);
        textHobby = (TextView)findViewById(R.id.Hobby);
        buttonHobby = (Button)findViewById(R.id.ChangeHobby);
        spJob = (Spinner) findViewById(R.id.JobSelect);
        checkTerms = (CheckBox)findViewById(R.id.TermsCheck);
        textTerms = (TextView)findViewById(TextTerms);
        sendCheck = (Button)findViewById(R.id.ImportCheck);



    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    @Override
    protected void onResume(){
        super.onResume();

        testArray = new ArrayList<>();
        testArray.add("a");
        testArray.add("b");


        buttonHobby.setOnClickListener(this);
        sendCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Log.v("確認","ボタン");
        switch (view.getId()){
            case R.id.ChangeHobby:
                Log.v("確認","ボタン1");

                break;
            case R.id.ImportCheck:
                Log.v("確認","ボタン2");


                HumanParcelable human = new HumanParcelable(
                        etFname.getText().toString(),
                        etLname.getText().toString(),
                        radioSei.getCheckedRadioButtonId(),
                        etTel.getText().toString(),
                        testArray,
                        spJob.getSelectedItem().toString()
                );

                intent =new Intent(this,ProfileCheck.class);
                intent.putExtra("profile",human);

                startActivity(intent);

                break;
        }

    }
}
