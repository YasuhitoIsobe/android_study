package com.example.uu097376.profileregistrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by uu097376 on 2016/12/08.
 */

public class ProfileCheck extends AppCompatActivity {

    TextView firstName;
    TextView lastName;
    TextView number;
    TextView gender;
    TextView hobby;
    TextView job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_check_activity);

        Intent intent = getIntent();
        HumanParcelable human = intent.getParcelableExtra("profile");

        firstName = (TextView)findViewById(R.id.FirstName);
        number = (TextView)findViewById(R.id.Number);
        gender = (TextView)findViewById(R.id.Gender);
        hobby = (TextView)findViewById(R.id.Ht);
        job = (TextView)findViewById(R.id.Job);


        firstName.setText(human.getFirstName() + " " + human.getLastName());
        String genderString = new String();
        if(human.getGender() == 0){
            genderString = "男";
        }else{
            genderString = "女";
        }
        gender.setText(genderString);
        number.setText(human.getTel());

        StringBuilder hobbys = new StringBuilder();
        if(human.getHobby().size() == 0) {
            for (int i = 0; i < human.getHobby().size(); i++) {
                if (i != 0)
                    hobbys.append(",");
                hobbys.append(human.getHobby().get(i));
            }
            hobby.setText(hobbys.toString());
        }
        job.setText(human.getJob());
    }
}
