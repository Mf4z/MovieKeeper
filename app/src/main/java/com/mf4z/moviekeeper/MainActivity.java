package com.mf4z.moviekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private MovieRecyclerAdapter mMovieRecyclerAdapter;
    private RecyclerView mRecyclerItems;
    private LinearLayoutManager mMovieLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MovieActivity.class)); //Intent to MovieActivity
            }
        });

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movies, R.id.nav_genres,R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_movies :
                        displayMovies();
                        break;

                    case R.id.nav_genres :
                        handleSelection("Genres");
                        break;

                    case R.id.nav_share :
                        handleSelection("Share");
                        break;

                    case R.id.nav_send :
                        handleSelection("Send");
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

//        navigationView.setNavigationItemSelectedListener(this);

        initializeDisplayContent(); //Displays movies
    }

    private void handleSelection(String message) {
        View view = findViewById(R.id.list_items);
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();

    }


    @Override
    protected void onResume() {
        super.onResume();
        mMovieRecyclerAdapter.notifyDataSetChanged();

    }

    private void initializeDisplayContent() {

        mRecyclerItems = (RecyclerView) findViewById(R.id.list_items);
        //Instantiate new LinearLayout Manager to manage recycler view
        mMovieLayoutManager = new LinearLayoutManager(this);

        //Get movies to display within the recycler view
        List<MovieInfo> movies = DataManager.getInstance().getMovies();

        //Create movies recycler adapter
        mMovieRecyclerAdapter = new MovieRecyclerAdapter(this,movies);

        //set adapter to recylcer view
        displayMovies();
    }

    private void displayMovies() {
        mRecyclerItems.setLayoutManager(mMovieLayoutManager); //set Layout manager to recyler view
        mRecyclerItems.setAdapter(mMovieRecyclerAdapter);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_movies).setChecked(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
