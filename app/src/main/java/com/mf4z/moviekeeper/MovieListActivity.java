package com.mf4z.moviekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private ArrayAdapter<MovieInfo> mAdapterMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieListActivity.this,MainActivity.class)); //Intent to MainActivity
            }
        });


        initializeDisplayContent();

    }


    @Override
    protected void onResume() {
        super.onResume();
        mAdapterMovies.notifyDataSetChanged(); //Notifies the adapter of new data added to accommodate it
    }

    private void initializeDisplayContent() {


        final ListView listMovies = (ListView) findViewById(R.id.list_movies);    //Marked as final so it can be referenced in setOnItemClickListener

        //List of Movies
        List<MovieInfo> movies = DataManager.getInstance().getMovies();

        //Adapter to load and handle Movies for ListView
        mAdapterMovies = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,movies);

        //Set adapter to ListView
        listMovies.setAdapter(mAdapterMovies);


        //Set a click even to the ListView item
        listMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MovieListActivity.this,MainActivity.class);

//                MovieInfo movie = (MovieInfo) listMovies.getItemAtPosition(position);  //Movie that corresponds to the clicked one

                //Use intent extra to pass data to MainActivity
                intent.putExtra(MainActivity.MOVIE_POSITION,position);
                startActivity(intent);

            }
        } );
    }

}
