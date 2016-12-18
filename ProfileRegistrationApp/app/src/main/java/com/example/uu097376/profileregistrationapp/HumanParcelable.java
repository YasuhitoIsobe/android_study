package com.example.uu097376.profileregistrationapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by uu097376 on 2016/12/08.
 */

public class HumanParcelable implements Parcelable {

    private String firstName;
    private String lastName;
    private int sex;
    private String tel;
    private boolean[] hobbySelectedArray;
    private int job;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(firstName);
        out.writeString(lastName);
        out.writeInt(sex);
        out.writeString(tel);
        out.writeBooleanArray(hobbySelectedArray);
        out.writeInt(job);
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
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.sex = in.readInt();
        this.tel = in.readString();
        this.hobbySelectedArray = in.createBooleanArray();
        this.job = in.readInt();
    }

    public HumanParcelable(String firstName, String lastName, int sex, String tel, boolean[] hobbySelectedArray,int job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.tel = tel;
        this.hobbySelectedArray = hobbySelectedArray;
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

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean[] getHobbySelecteArray() {
        return this.hobbySelectedArray;
    }

    public void setHobbySelectedArray(boolean[] hobbySelectedArray) {
        this.hobbySelectedArray = hobbySelectedArray;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }
}
