package com.mf4z.moviekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    }
}
