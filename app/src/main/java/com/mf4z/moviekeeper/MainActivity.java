package com.mf4z.moviekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_POSITION = "com.mf4z.moviekeeper.MOVIE_POSITION"; //Key value pair to identify passed info via intent extra
    public static final int POSITION_NOT_SET = -1;
    private MovieInfo mMovie;
    private boolean mIsNewMovie;
    private EditText mTextMovieTitle;
    private EditText mTextMovieDesc;
    private Spinner mSpinnerGenre;
    private int mMoviePosition;
    private boolean mIsCancelling;
    private MovieActivityViewModel mViewModel;

    //States that cannot be derived from intent, to use Instance State to get is recommended
    /*private String mOriginalMovieGenreId;
    private String mOriginalMovieTitle;
    private String mOriginalMovieText;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating instance of view model provider to manage ViewModel class. Useful in handling configuration change
        // (screen rotation,so data is not lost after activity is destroyed)
        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())); //Boiler plate code

        //Assign view model
        mViewModel = viewModelProvider.get(MovieActivityViewModel.class); //gives us back the reference to our view model

        //Check if savedInstanceState is null to restore state
        if (mViewModel.mIsnewlyCreated && savedInstanceState != null)
            mViewModel.restoreState(savedInstanceState);
        //Make newly created false
        mViewModel.mIsnewlyCreated = false;

        mSpinnerGenre = (Spinner) findViewById(R.id.spinner_genre);

        //List of Genre
        List<GenreInfo> genre = DataManager.getInstance().getGenres();

        //Array Adapter to handle populating the spinner
        ArrayAdapter<GenreInfo> adapterGenre = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, genre);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //Set  Layout for dropdown
        mSpinnerGenre.setAdapter(adapterGenre); //Setting adapter to spinner

        //Read info passed between the activity via intent extra
        readDisplayStateValues();

        //Saves original movie values
        saveOriginalMovieValues();


        mTextMovieTitle = (EditText) findViewById(R.id.editText_movie);
        mTextMovieDesc = (EditText) findViewById(R.id.editText_description);

        if (!mIsNewMovie) {   //Display if movie is existing note
            //Method to display Movies
            displayMovies(mSpinnerGenre, mTextMovieTitle, mTextMovieDesc);
        }
    }

    private void saveOriginalMovieValues() {
        if (mIsNewMovie)
            return;

        mViewModel.mOriginalMovieGenreId = mMovie.getGenre().getGenreId();
        mViewModel.mOriginalMovieTitle = mMovie.getTitle();
        mViewModel.mOriginalMovieText = mMovie.getText();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_movie, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_send_mail:
                sendEmail();
                return true;

            case R.id.action_cancel:
                mIsCancelling = true; //Check if user wants to cancel
                finish(); //Used when a user wants to end an activity and return to previous activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mIsCancelling){ //If canceling is true remove new movie

            if (mIsNewMovie) //Check if it is a new movie, if true remove.
                DataManager.getInstance().removeMovie(mMoviePosition); //removing new movie
            else {
                //Save previous movie  values
                savePreviousMovieValues();
            }
        }
        else { //Save existing movie
            //Saves the movies when back is pressed and onPause is called
            saveMovies();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null)
            mViewModel.saveState(outState);
    }

    private void savePreviousMovieValues() {
        GenreInfo genreInfo = DataManager.getInstance().getGenre(mViewModel.mOriginalMovieGenreId);
        mMovie.setGenre(genreInfo);
        mMovie.setTitle(mViewModel.mOriginalMovieTitle);
        mMovie.setText(mViewModel.mOriginalMovieText);
    }

    private void saveMovies() {
        mMovie.setGenre((GenreInfo)mSpinnerGenre.getSelectedItem());
        mMovie.setTitle(mTextMovieTitle.getText().toString());
        mMovie.setText(mTextMovieDesc.getText().toString());
    }

    private void sendEmail() {

        String genre = mSpinnerGenre.getSelectedItem().toString();
        String subject = mTextMovieTitle.getText().toString();
        String text = "Check out what I'm watching, " + genre + " : \"" + subject + "\" \n" + mTextMovieDesc.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822"); //mime type for email
        intent.putExtra(Intent.EXTRA_SUBJECT, subject); //set the subject of the mail
        intent.putExtra(Intent.EXTRA_TEXT, text); //text in the email
        startActivity(intent);

    }

    private void displayMovies(Spinner spinnerGenre, EditText textMovieTitle, EditText textMovieDesc) {

        //List  of genres
        List<GenreInfo> genres = DataManager.getInstance().getGenres();
        int genreIndex = genres.indexOf(mMovie.getGenre());

        spinnerGenre.setSelection(genreIndex);
        textMovieTitle.setText(mMovie.getTitle());
        textMovieDesc.setText(mMovie.getText());
    }

    private void readDisplayStateValues() {

        Intent intent = getIntent(); //Reference to the intent
        //Get the data passed via the intent
        int position = intent.getIntExtra(MOVIE_POSITION, POSITION_NOT_SET);

        //Check if it's a new note or not
        mIsNewMovie = position == POSITION_NOT_SET;
        if (mIsNewMovie) { //If it's new movie, create a movie
            //Create Movie
            createMovie();
        }
        else {
            //Get Movie passed by it's position
            mMovie = DataManager.getInstance().getMovies().get(position);
        }
    }

    private void createMovie() {
        DataManager dm = DataManager.getInstance();
        mMoviePosition = dm.createNewNote();
        mMovie = dm.getMovies().get(mMoviePosition);
    }
}
