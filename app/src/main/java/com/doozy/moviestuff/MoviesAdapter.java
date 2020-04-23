package com.doozy.moviestuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private final MoviesAdapterOnClickHandler mClickHandler;
    private List<Movie> mList = new ArrayList<>();

    public MoviesAdapter(MoviesAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;

    }

    public interface MoviesAdapterOnClickHandler {
        void onClick(String movieData);
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView movieTxt;
        public MoviesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTxt = itemView.findViewById(R.id.movies_txt);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {

        Movie movie = mList.get(position);
        int movieId = movie.getMovieId();

        holder.movieTxt.setText(String.valueOf(movieId));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setMoviesData(List<Movie> resultMoviesList) {
        mList = resultMoviesList;
        notifyDataSetChanged();
    }
}
