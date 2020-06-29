package com.mf4z.moviekeeper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    @Before
    public void setUp(){
         DataManager dm = DataManager.getInstance();
         dm.getMovies().clear();
         dm.initializeExampleMovies();
    }

    @Test
    public void createNewMovie() {
        DataManager dm = DataManager.getInstance();
        final  GenreInfo genre = dm.getGenre("android_async");
        final  String movieTitle = "Test Unit Title";
        final  String movieText = "Test Text Movie";

        int movieIndex = dm.createNewMovie();

        MovieInfo newMovie = dm.getMovies().get(movieIndex);
        newMovie.setGenre(genre);
        newMovie.setTitle(movieTitle);
        newMovie.setText(movieText);

        //Test MovieInfo to capare with above newMovie info to check functionality
        MovieInfo compareMovie = dm.getMovies().get(movieIndex);

        //using assertEquals method to check for equality of values
        assertEquals(genre,compareMovie.getGenre());
        assertEquals(movieTitle,compareMovie.getTitle());
        assertEquals(movieText,compareMovie.getText());
    }


    @Test
    public void findSimilarMovies() {
        DataManager dm = DataManager.getInstance();
        final GenreInfo genre = dm.getGenre("android_async");
        final String movieTitle = "Test note title";
        final String movieText1 = "This is the body text of my test note";
        final String movieText2  = "This is the body of my second test note";

        int movieIndex1 = dm.createNewMovie();
        MovieInfo newMovie1 = dm.getMovies().get(movieIndex1);
        newMovie1.setGenre(genre);
        newMovie1.setTitle(movieTitle);
        newMovie1.setText(movieText1);

        int movieIndex2 = dm.createNewMovie();
        MovieInfo newMovie2 = dm.getMovies().get(movieIndex2);
        newMovie2.setGenre(genre);
        newMovie2.setTitle(movieTitle);
        newMovie2.setText(movieText2);

        int foundIndex1 = dm.findMovie(newMovie1);
        assertEquals(movieIndex1, foundIndex1);

        int foundIndex2 = dm.findMovie(newMovie2);
        assertEquals(movieIndex2, foundIndex2);
    }
}