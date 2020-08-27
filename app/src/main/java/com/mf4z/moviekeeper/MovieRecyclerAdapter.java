package com.mf4z.moviekeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<MovieInfo> mMovies;

    public MovieRecyclerAdapter(Context context, List<MovieInfo> movies) {
        mContext = context;
        mMovies = movies;
        //Create layout inflater using current context
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_movie_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MovieInfo movie = mMovies.get(position);
        holder.mTextGenre.setText(movie.getGenre().getTitle());
        holder.mTextTitle.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    //Holds information to be viewd by the item view in the recycler adapter
    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView mTextGenre;
        public final TextView mTextTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextGenre = itemView.findViewById(R.id.text_course);
            mTextTitle = itemView.findViewById(R.id.text_title);
        }
    }
}
