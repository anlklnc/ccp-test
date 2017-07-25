package com.test.quicktest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by asd on 12.7.2017.
 */

public class ContentNetwork {

    final String BASE_URL = "https://planet.thy.com:1051/";

    ContentClient client;

    public interface ContentClient {
        @GET("/BlockContentService/GetMovies")
        Call<List<Movie>> movieList();
    }

    public void getMovieInfo(NetworkListener listener){ setRequest(client.movieList(), listener); }
    public String getMovieInfo() { return getUrl(client.movieList()); }

    private void setRequest(Call call, final NetworkListener listener) {

        Log.i("!!!", "setRequest: "+call.request().url());
        Callback callback = new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                Log.i("!!!", "contentApi.onResponse");
                listener.onResponse(data);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("!!!", "Connection failed: " + t.getCause());
                listener.onError(-1);
            }
        };

        call.enqueue(callback);
    }

    public ContentNetwork() {

        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        client =  retrofit.create(ContentClient.class);
    }

    private String getUrl(Call<?> call) {
        return call.request().url().toString();
    }
}
