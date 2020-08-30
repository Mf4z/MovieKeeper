package com.mf4z.moviekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class MovieListActivity extends AppCompatActivity {
    private MovieRecyclerAdapter mMovieRecyclerAdapter;

//    private ArrayAdapter<MovieInfo> mAdapterMovies;

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
                startActivity(new Intent(MovieListActivity.this, MovieActivity.class)); //Intent to MovieActivity
            }
        });


        initializeDisplayContent();

    }


    @Override
    protected void onResume() {
        super.onResume();
        mMovieRecyclerAdapter.notifyDataSetChanged();

    }

    private void initializeDisplayContent() {

        final RecyclerView recyclerMovies = (RecyclerView) findViewById(R.id.list_movies);
        final LinearLayoutManager movieLayoutManager = new LinearLayoutManager(this); //Instantiate new LinearLayout Manager to manage recycler view
        recyclerMovies.setLayoutManager(movieLayoutManager); //set Layout manager to recyler view

        //Get movies to display within the recycler view
        List<MovieInfo> movies = DataManager.getInstance().getMovies();

        //Create movies recycler adapter
        mMovieRecyclerAdapter = new MovieRecyclerAdapter(this,movies);

        //set adapter to recylcer view
        recyclerMovies.setAdapter(mMovieRecyclerAdapter);
    }

}
