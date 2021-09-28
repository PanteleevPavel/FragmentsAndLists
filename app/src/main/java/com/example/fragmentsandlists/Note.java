package com.example.fragmentsandlists;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {
    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
    private final int noteDescriptionIndex;
    private final String noteName;
    private final Date createDate;

    public Note(int noteDescriptionIndex, String noteName) {
        this.noteDescriptionIndex = noteDescriptionIndex;
        this.noteName = noteName;
        this.createDate = new Date();
    }

    protected Note(Parcel in) {
        noteDescriptionIndex = in.readInt();
        noteName = in.readString();
        createDate = new Date();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getDescriptionIndex());
        dest.writeString(getCityName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getDescriptionIndex() {
        return noteDescriptionIndex;
    }

    public String getCityName() {
        return noteName;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
