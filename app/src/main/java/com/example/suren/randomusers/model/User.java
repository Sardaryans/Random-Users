package com.example.suren.randomusers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{

    private Name name;
    private Picture picture;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    protected User(Parcel in) {
        name = in.readParcelable(Name.class.getClassLoader());
        picture = in.readParcelable(Picture.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(name, i);
        parcel.writeParcelable(picture, i);
    }
}
