package com.doozy.moviestuff;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Utility functions to handle Movies JSON data.
 */
public final class MoviesJsonUtils {



    public static List<Movie> getMoviesObjectList(Context context, String moviesJsonString)
            throws JSONException {

        List<Movie> moviesList = new ArrayList<>();

        JSONObject moviesJSONObject = new JSONObject(moviesJsonString);
        JSONArray movieresultsJSONArray = moviesJSONObject.getJSONArray("results");

        for (int i = 0; i < movieresultsJSONArray.length(); i++){

            JSONObject movieJSONObject = movieresultsJSONArray.getJSONObject(i);

            Movie movie = new Movie();
            movie.setMovieId(movieJSONObject.getInt("id"));
            

            moviesList.add(movie);

        }

        return moviesList;
    }

}