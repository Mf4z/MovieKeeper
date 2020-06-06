package com.mf4z.moviekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_INFO = "com.mf4z.moviekeeper.MOVIE_INFO"; //Key value pair to identify passed info via intent extra
    private MovieInfo mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerGenre = (Spinner) findViewById(R.id.spinner_genre);

        //List of Genre
        List<GenreInfo> genre = DataManager.getInstance().getGenre();

        //Array Adapter to handle populating the spinner
        ArrayAdapter<GenreInfo> adapterGenre = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,genre);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item ); //Set  Layout for dropdown
        spinnerGenre.setAdapter(adapterGenre); //Setting adapter to spinner

        //Read info passed between the activity via intent extra
        readDisplayStateValues();


        EditText textMovieTitle = (EditText) findViewById(R.id.editText_movie);
        EditText textMovieDesc  = (EditText) findViewById(R.id.editText_description);

        //Method to display Movies
        displayMovies(spinnerGenre,textMovieTitle,textMovieDesc);
    }

    private void displayMovies(Spinner spinnerGenre, EditText textMovieTitle, EditText textMovieDesc) {

        //List  of genres
        List<GenreInfo> genres = DataManager.getInstance().getGenre();
        int genreIndex = genres.indexOf(mMovie.getGenre());

        spinnerGenre.setSelection(genreIndex);
        textMovieTitle.setText(mMovie.getTitle());
         textMovieDesc.setText(mMovie.getText() );
    }

    private void readDisplayStateValues() {

        Intent intent = getIntent(); //R eference to the intent
        //Get the data passed via the intent
        mMovie = intent .getParcelableExtra(MOVIE_INFO);

    }
}
