package com.example.newsapp;

import android.content.Context;
import android.widget.Toast;

import com.example.newsapp.models.newsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class requestManager {

    Context context;
    String api_key="de42c5dd46d54e0b80986cd05a44fd88";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadlines(onFetchDataListener listener, String query){
        callNewsApi callNewsApi = retrofit.create(callNewsApi.class);
        Call<newsApiResponse> call = callNewsApi.callHealines("in", query, api_key);
        try{
            call.enqueue(new Callback<newsApiResponse>() {
                @Override
                public void onResponse(Call<newsApiResponse> call, Response<newsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchdata(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<newsApiResponse> call, Throwable t) {
                    listener.onError("Request Failed");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public requestManager(Context context) {
        this.context = context;
    }

    public interface callNewsApi{
        @GET("top-headlines")
        Call<newsApiResponse> callHealines(
                @Query("country") String country,
                @Query("q") String query,
                @Query("apiKey") String api_key
        );
    }
}
