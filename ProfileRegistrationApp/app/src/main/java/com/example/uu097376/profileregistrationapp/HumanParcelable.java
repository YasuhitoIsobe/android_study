package com.example.uu097376.profileregistrationapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uu097376 on 2016/12/08.
 */

public class HumanParcelable implements Parcelable {

    private String firstName;
    private String lastName;
    private String gender;
    private String tel;
    private List<String> hobby;
    private String job;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(firstName);
        out.writeString(lastName);
        out.writeString(gender);
        out.writeString(tel);
        out.writeStringList(hobby);
        out.writeString(job);

    }

    public static final Parcelable.Creator<HumanParcelable> CREATOR
            = new Parcelable.Creator<HumanParcelable>() {
        public HumanParcelable createFromParcel(Parcel in) {
            return new HumanParcelable(in);
        }

        public HumanParcelable[] newArray(int size) {
            return new HumanParcelable[size];
        }
    };

    private HumanParcelable(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        gender = in.readString();
        tel = in.readString();
        hobby = in.createStringArrayList();
        job = in.readString();
    }

    public HumanParcelable(String firstName, String lastName, String gender, String tel, List hobby,String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.tel = tel;
        this.hobby = hobby;
        this.job = job;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List getHobby() {
        return hobby;
    }

    public void setHobby(ArrayList hobby) {
        this.hobby = hobby;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
