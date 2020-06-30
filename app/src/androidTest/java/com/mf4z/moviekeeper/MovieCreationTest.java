package com.mf4z.moviekeeper;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MovieCreationTest {

    @Rule
    public ActivityTestRule<MovieListActivity> mMovieListActivityRule =
            new ActivityTestRule<>(MovieListActivity.class);

    @Test
    public void createNewMovie(){}
}