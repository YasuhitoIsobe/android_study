package com.example.uu097376.profileregistrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by uu097376 on 2016/12/08.
 */

public class ProfileCheck extends AppCompatActivity {

    TextView name;
    TextView number;
    TextView sex;
    TextView hobby;
    TextView job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_check_activity);

        // 性別の配列取得
        String[] sexArray = getResources().getStringArray(R.array.sex_values);

        // 趣味の配列取得
        String[] hobbyArray = getResources().getStringArray(R.array.hobby_values);

        // 仕事の配列取得
        String[] jobArray = getResources().getStringArray(R.array.job_values);

        Intent intent = getIntent();
        HumanParcelable human = intent.getParcelableExtra("profile");

        name = (TextView)findViewById(R.id.FirstName);
        number = (TextView)findViewById(R.id.Number);
        sex = (TextView)findViewById(R.id.Sex);
        hobby = (TextView)findViewById(R.id.Ht);
        job = (TextView)findViewById(R.id.Job);

        name.setText(human.getFirstName() + " " + human.getLastName());
        sex.setText(sexArray[human.getSex()]);
        number.setText(human.getTel());
        hobby.setText(ProfileResistrationAppUtil.getHobbyDispString(getResources().getStringArray(R.array.hobby_values), human.getHobbySelecteArray()));
        job.setText(jobArray[human.getJob()]);
    }
}
