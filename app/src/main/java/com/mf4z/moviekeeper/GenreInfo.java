package com.mf4z.moviekeeper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public final class GenreInfo implements Parcelable {
    private final String mGenreId;
    private final String mTitle;
    private final List<ModuleInfo> mModules;

    public GenreInfo(String genreId, String title, List<ModuleInfo> modules) {
        mGenreId = genreId;
        mTitle = title;
        mModules = modules;
    }

    private GenreInfo(Parcel source) {
        mGenreId = source.readString();
        mTitle = source.readString();
        mModules = new ArrayList<>();
        source.readTypedList(mModules, ModuleInfo.CREATOR);
    }

    public String getGenreId() {
        return mGenreId;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<ModuleInfo> getModules() {
        return mModules;
    }

    public boolean[] getModulesCompletionStatus() {
        boolean[] status = new boolean[mModules.size()];

        for(int i=0; i < mModules.size(); i++)
            status[i] = mModules.get(i).isComplete();

        return status;
    }

    public void setModulesCompletionStatus(boolean[] status) {
        for(int i=0; i < mModules.size(); i++)
            mModules.get(i).setComplete(status[i]);
    }

    public ModuleInfo getModule(String moduleId) {
        for(ModuleInfo moduleInfo: mModules) {
            if(moduleId.equals(moduleInfo.getModuleId()))
                return moduleInfo;
        }
        return null;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreInfo that = (GenreInfo) o;

        return mGenreId.equals(that.mGenreId);

    }

    @Override
    public int hashCode() {
        return mGenreId.hashCode();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mGenreId);
        dest.writeString(mTitle);
        dest.writeTypedList(mModules);
    }

    public static final Creator<GenreInfo> CREATOR =
            new Creator<GenreInfo>() {

                @Override
                public GenreInfo createFromParcel(Parcel source) {
                    return new GenreInfo(source);
                }

                @Override
                public GenreInfo[] newArray(int size) {
                    return new GenreInfo[size];
                }
            };

}
