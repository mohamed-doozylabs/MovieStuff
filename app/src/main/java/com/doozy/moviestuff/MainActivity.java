package com.doozy.moviestuff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.doozy.moviestuff.MoviesAdapter.MoviesAdapterOnClickHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MoviesAdapterOnClickHandler {

    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private List<Movie> moviesList;

    private TextView errotTxt;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesList = new ArrayList<>();
        moviesRecyclerView = findViewById(R.id.recyclerview_movies);
        moviesAdapter = new MoviesAdapter( this);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(mLayoutManager);
        moviesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        moviesRecyclerView.setAdapter(moviesAdapter);

        new MoviesTask().execute("popular");

        progressBar = findViewById(R.id.progress_bar);

    }

    @Override
    public void onClick(String movieData) {

    }

    public class MoviesTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {

            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String sortBy = params[0];
            URL theMovieDbUrl = NetworkUtils.buildUrl(sortBy);

            try {
                String jsonMoviesResponse = NetworkUtils
                        .getResponseFromHttpUrl(theMovieDbUrl);

                moviesList = MoviesJsonUtils.getMoviesObjectList(MainActivity.this, jsonMoviesResponse);

                return moviesList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> resultMoviesList) {
//            moviesAdapter.notifyDataSetChanged();
//            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (resultMoviesList != null) {
//                showWeatherDataView();
                moviesAdapter.setMoviesData(resultMoviesList);
            } else {
//                showErrorMessage();
            }
        }
    }

}
