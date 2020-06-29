package com.mf4z.moviekeeper;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

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
        assertEquals(compareMovie.getGenre(),genre);
        assertEquals(compareMovie.getTitle(),movieTitle);
        assertEquals(compareMovie.getText(),movieText);
    }
}