package com.mf4z.moviekeeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

     static DataManager sDataManager; //Need to be statis to be used by the @BeforeClass

    @BeforeClass
    public static void classSetUp(){

        sDataManager = DataManager.getInstance();
    }

    @Before
    public void setUp(){
         sDataManager.getMovies().clear();
         sDataManager.initializeExampleMovies();
    }

    @Test
    public void createNewMovie() {
        final  GenreInfo genre = sDataManager.getGenre("android_async");
        final  String movieTitle = "Test Unit Title";
        final  String movieText = "Test Text Movie";

        int movieIndex = sDataManager.createNewMovie();

        MovieInfo newMovie = sDataManager.getMovies().get(movieIndex);
        newMovie.setGenre(genre);
        newMovie.setTitle(movieTitle);
        newMovie.setText(movieText);

        //Test MovieInfo to capare with above newMovie info to check functionality
        MovieInfo compareMovie = sDataManager.getMovies().get(movieIndex);

        //using assertEquals method to check for equality of values
        assertEquals(genre,compareMovie.getGenre());
        assertEquals(movieTitle,compareMovie.getTitle());
        assertEquals(movieText,compareMovie.getText());
    }


    @Test
    public void findSimilarMovies() {
        final GenreInfo genre = sDataManager.getGenre("android_async");
        final String movieTitle = "Test note title";
        final String movieText1 = "This is the body text of my test note";
        final String movieText2  = "This is the body of my second test note";

        int movieIndex1 = sDataManager.createNewMovie();
        MovieInfo newMovie1 = sDataManager.getMovies().get(movieIndex1);
        newMovie1.setGenre(genre);
        newMovie1.setTitle(movieTitle);
        newMovie1.setText(movieText1);

        int movieIndex2 = sDataManager.createNewMovie();
        MovieInfo newMovie2 = sDataManager.getMovies().get(movieIndex2);
        newMovie2.setGenre(genre);
        newMovie2.setTitle(movieTitle);
        newMovie2.setText(movieText2);

        int foundIndex1 = sDataManager.findMovie(newMovie1);
        assertEquals(movieIndex1, foundIndex1);

        int foundIndex2 = sDataManager.findMovie(newMovie2);
        assertEquals(movieIndex2, foundIndex2);
    }

    @Test
    public void createNewMovieOneStepCreation(){
        final  GenreInfo genre = sDataManager.getGenre("android_async");
        final  String movieTitle = "Test Unit Title";
        final  String movieText = "Test Text Movie";

        int movieIndex = sDataManager.createNewMovie();

        MovieInfo newMovie = sDataManager.getMovies().get(movieIndex);
        newMovie.setGenre(genre);
        newMovie.setTitle(movieTitle);
        newMovie.setText(movieText);

        //Test MovieInfo to capare with above newMovie info to check functionality
        MovieInfo compareMovie = sDataManager.getMovies().get(movieIndex);

        //using assertEquals method to check for equality of values
        assertEquals(genre,compareMovie.getGenre());
        assertEquals(movieTitle,compareMovie.getTitle());
        assertEquals(movieText,compareMovie.getText());


    }
}