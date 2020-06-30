package com.mf4z.moviekeeper;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;
import static androidx.test.espresso.Espresso.pressBack;


@RunWith(AndroidJUnit4.class)
public class MovieCreationTest {

    static DataManager sDataManager;

    @BeforeClass
    public static void classSetUp(){
        sDataManager = DataManager.getInstance();
    }

    @Rule
    public ActivityTestRule<MovieListActivity> mMovieListActivityRule =
            new ActivityTestRule<>(MovieListActivity.class);

    @Test
    public void createNewMovie(){

         final GenreInfo genre = sDataManager.getGenre("java_lang");
        final String  movieTitle = "Test movie title";
        final String  movieText = "This is a test movie description, should be fun!";

 /*       ViewInteraction fabNewMovie = onView(withId(R.id.fab));
        fabNewMovie.perform(click());*/


 //NB: Steps have to be sequnetial as would be when user interacts with activities

        //Prefered way of doing the above
        onView(withId(R.id.fab)).perform(click()); //Locating the view and peforming and action on it

        //Select item from spinner, spinner particular action,not needed for listviews
        onView(withId(R.id.spinner_genre)).perform(click());

        //Perform click on spinner selcetion
        onData(allOf(instanceOf(GenreInfo.class),equalTo(genre))).perform(click());

        onView(withId(R.id.editText_movie)).perform(typeText(movieTitle));
        onView(withId(R.id.editText_description)).perform(typeText(movieText),
                closeSoftKeyboard());

        pressBack(); //to go back to the previous activity
    }
}