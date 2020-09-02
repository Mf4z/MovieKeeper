package com.mf4z.moviekeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GenreRecyclerAdapter extends RecyclerView.Adapter<GenreRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<GenreInfo> mGenres;

    public GenreRecyclerAdapter(Context context, List<GenreInfo> genres) {
        mContext = context;
        mGenres = genres;
        //Create layout inflater using current context
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_genre_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GenreInfo genre = mGenres.get(position);
        holder.mTextGenre.setText(genre.getTitle());
        holder.mCurrentPosition = position;  //Holds current item position
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    //Holds information to be viewd by the item view in the recycler adapter
    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mTextGenre;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextGenre = itemView.findViewById(R.id.text_course);

            //Adding click event to item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,mTextGenre.getText(),Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }
}
