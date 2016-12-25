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
    TextView gender;
    TextView hobby;
    TextView job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_check_activity);

        String[] genderArray = getResources().getStringArray(R.array.gender_values);
        String[] hobbyArray = getResources().getStringArray(R.array.hobby_list);

        Intent intent = getIntent();
        HumanParcelable human = intent.getParcelableExtra("profile");

        name = (TextView)findViewById(R.id.FirstName);
        number = (TextView)findViewById(R.id.Number);
        gender = (TextView)findViewById(R.id.Gender);
        hobby = (TextView)findViewById(R.id.Ht);
        job = (TextView)findViewById(R.id.Job);


        name.setText(human.getFirstName() + " " + human.getLastName());
        gender.setText(genderArray[human.getGender()]);
        number.setText(human.getTel());
        hobby.setText(HobbyStringSet.hobbySet(human.getHobby(),hobbyArray));

/*        StringBuilder hobbyString = new StringBuilder();
        if(human.getHobby() != null) {
*//*            for (int i = 0; i < human.getHobby().size(); i++) {
                if (i != 0)
                    hobbyString.append(",");
                hobbyString.append(human.getHobby().get(i));
            }*//*
            hobby.setText(hobbyString.toString());
        }*/
        job.setText(human.getJob());
    }
}
