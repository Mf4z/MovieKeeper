package com.mf4z.moviekeeper;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

//State stored separate from activity, used when activity in process, eg screen rotation
public class MovieActivityViewModel extends ViewModel {

    public static final String ORIGINAL_MOVIE_GENRE_ID = "com.mf4z.moviekeeper.ORIGINAL_MOVIE_GENRE_ID";
    public static final String ORIGINAL_MOVIE_TITLE = "com.mf4z.moviekeeper.ORIGINAL_MOVIE_TITLE";
    public static final String ORIGINAL_MOVIE_TEXT = "com.mf4z.moviekeeper.ORIGINAL_MOVIE_TEXT";
    public String mOriginalMovieGenreId;
    public String mOriginalMovieTitle;
    public String mOriginalMovieText;
    public boolean mIsnewlyCreated = true;

    public void saveState(Bundle outState) {

        outState.putString(ORIGINAL_MOVIE_GENRE_ID,mOriginalMovieGenreId);
        outState.putString(ORIGINAL_MOVIE_TITLE,mOriginalMovieTitle);
        outState.putString(ORIGINAL_MOVIE_TEXT,mOriginalMovieText);
    }

    public void restoreState(Bundle inState){
        mOriginalMovieGenreId = inState.getString(ORIGINAL_MOVIE_GENRE_ID);
        mOriginalMovieTitle = inState.getString(ORIGINAL_MOVIE_TITLE);
        mOriginalMovieText = inState.getString(ORIGINAL_MOVIE_TEXT);
    }
}
