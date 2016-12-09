package jp.co.n_sysdes.y_isobe.parcelabletest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by YASUHITO on 2016/12/09.
 */
public class ParcelableObj implements Parcelable {
    private String name;
    private int age;
    private List<String> belongings;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.name);
        out.writeInt(this.age);
        out.writeStringList(this.belongings);
    }

    public static final Parcelable.Creator<ParcelableObj> CREATOR
            = new Parcelable.Creator<ParcelableObj>() {
        public ParcelableObj createFromParcel(Parcel in) {
            return new ParcelableObj(in);
        }

        public ParcelableObj[] newArray(int size) {
            return new ParcelableObj[size];
        }
    };

    private ParcelableObj(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.belongings = in.createStringArrayList();
    }

    public ParcelableObj(String name, int age, List<String> belongings) {
        this.name = name;
        this.age  = age;
        this.belongings = belongings;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public List<String> getBelongings() {
        return this.belongings;
    }
}
