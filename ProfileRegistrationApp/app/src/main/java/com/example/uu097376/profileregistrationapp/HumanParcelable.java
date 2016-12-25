package com.example.uu097376.profileregistrationapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by uu097376 on 2016/12/08.
 */

public class HumanParcelable implements Parcelable {

    private String firstName;
    private String lastName;
    private int gender;
    private String tel;
    private boolean[] hobbySelectedArray;
    private String job;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(firstName);
        out.writeString(lastName);
        out.writeInt(gender);
        out.writeString(tel);
        out.writeBooleanArray(hobbySelectedArray);
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
        gender = in.readInt();
        tel = in.readString();
        hobbySelectedArray = in.createBooleanArray();
        job = in.readString();
    }

    public HumanParcelable(String firstName, String lastName, int gender, String tel, boolean[] hobby,String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.tel = tel;
        this.hobbySelectedArray = hobby;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean[] getHobby() {
        return hobbySelectedArray;
    }

    public void setHobby(boolean[] hobby) {
        this.hobbySelectedArray = hobby;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
