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
        lastName = (TextView)findViewById(R.id.LastName);
        number = (TextView)findViewById(R.id.Number);
        gender = (TextView)findViewById(R.id.Gender);
        hobby = (TextView)findViewById(R.id.Ht);
        job = (TextView)findViewById(R.id.Job);


        firstName.setText(human.getFirstName());
        lastName.setText(human.getLastName());
        gender.setText(human.getSei());
        number.setText(human.getTel());
        job.setText(human.getJob());



        System.out.println(human.getFirstName());
        System.out.println(human.getLastName());
        System.out.println(human.getSei());
        System.out.println(human.getTel());
        System.out.println(human.getHobby().get(0));
        System.out.println(human.getJob());







    }
}
