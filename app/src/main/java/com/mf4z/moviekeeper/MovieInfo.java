package com.mf4z.moviekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class MovieInfo implements Parcelable {
    private GenreInfo mGenre;
    private String mTitle;
    private String mText;

    public MovieInfo(GenreInfo genre, String title, String text) {
        mGenre = genre;
        mTitle = title;
        mText = text;
    }

    private MovieInfo(Parcel parcel) {

        mGenre = parcel.readParcelable(GenreInfo.class.getClassLoader());
        mTitle = parcel.readString();
        mText = parcel.readString();
    }

    public GenreInfo getGenre() {
        return mGenre;
    }

    public void setGenre(GenreInfo genre) {
        mGenre = genre;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mGenre.getGenreId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieInfo that = (MovieInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeParcelable(mGenre,0);
        parcel.writeString(mTitle);
        parcel.writeString(mText);
    }


    public static final Parcelable.Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel parcel) {
            return new MovieInfo(parcel);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };
}
