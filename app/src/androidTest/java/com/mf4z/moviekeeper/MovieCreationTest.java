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
import static androidx.test.espresso.assertion.ViewAssertions.*;

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
        //Check Genre title corresponds to given one we are testing 'genre' variable
        onView(withId(R.id.spinner_genre)).check(matches(withSpinnerText(
                containsString(genre.getTitle())))); //Making sure what is displayed is the genre title

        onView(withId(R.id.editText_movie)).perform(typeText(movieTitle)).
                                //Not necessary to add .check() because typeText can be relied on
                check(matches(withText(containsString(movieTitle)))); //Can add check method to make use of the initalview we are interested in
        onView(withId(R.id.editText_description)).perform(typeText(movieText),
                closeSoftKeyboard());

        //Checking if the text contained matches
        onView(withId(R.id.editText_description)).check(matches(withText(containsString(movieText)))); //Not necessary to add .check() because typeText can be relied on

        pressBack(); //to go back to the previous activity


        //Testing movie creation logic working properly
        int index = sDataManager.getMovies().size() -1; //getting the last index
        MovieInfo movie = sDataManager.getMovies().get(index);
        assertEquals(genre,movie.getGenre());
        assertEquals(movieTitle,movie.getTitle());
        assertEquals(movieText,movie.getText());

    }
}