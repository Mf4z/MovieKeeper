package com.mf4z.moviekeeper;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.*;


@RunWith(AndroidJUnit4.class)
public class MovieCreationTest {

    @Rule
    public ActivityTestRule<MovieListActivity> mMovieListActivityRule =
            new ActivityTestRule<>(MovieListActivity.class);

    @Test
    public void createNewMovie(){
 /*       ViewInteraction fabNewMovie = onView(withId(R.id.fab));
        fabNewMovie.perform(click());*/

 //Prefered way of doing the above
        onView(withId(R.id.fab)).perform(click()); //Locating the view and peforming and action on it
        onView(withId(R.id.editText_movie)).perform(typeText("Test movie title"));
        onView(withId(R.id.editText_description)).perform(typeText("This is a test movie description, should be fun!"),
                closeSoftKeyboard());
    }
}