package com.example.zoilaharo.myapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class MatchesModel implements Parcelable {
    public String imageUrl;
    public boolean liked;
    public String name;
    public String uid;;

    public MatchesModel(){}

    public static final Creator<MatchesModel> CREATOR = new Creator<MatchesModel>() {

       @Override
        public MatchesModel createFromParcel(Parcel in) {
            return new MatchesModel();
        }

        @Override
        public MatchesModel[] newArray(int size) {

            return new MatchesModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(name);

    }
}
