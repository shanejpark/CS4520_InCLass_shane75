package com.example.demo_firstproject.inClass02;

import android.os.Parcel;
import android.os.Parcelable;

public class Profile implements Parcelable {
    private final String name, email, os, mood;
    private final int avatarID, moodID;

    public Profile(String name, String email, String os, String mood, int avatarID, int moodID) {
        this.name = name;
        this.email = email;
        this.os = os;
        this.mood = mood;
        this.avatarID = avatarID;
        this.moodID = moodID;
    }

    protected Profile(Parcel in) {
        name = in.readString();
        email = in.readString();
        os = in.readString();
        mood = in.readString();
        avatarID = in.readInt();
        moodID = in.readInt();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(os);
        dest.writeString(mood);
        dest.writeInt(avatarID);
        dest.writeInt(moodID);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getOs() {
        return os;
    }

    public String getMood() {
        return mood;
    }

    public int getAvatarID() {
        return avatarID;
    }

    public int getMoodID() {
        return moodID;
    }
}
