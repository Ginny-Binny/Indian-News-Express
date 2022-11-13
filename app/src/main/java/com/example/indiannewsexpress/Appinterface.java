package com.example.indiannewsexpress;

import com.example.indiannewsexpress.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Appinterface {
    //interface to get top headline according to country and category
    String base_url="https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<NewsApiResponse> getNews(
        @Query("country") String country,
        @Query("pageSize") int pagesize,
        @Query("apikey") String apikey
    );
    @GET("top-headlines")
    Call<NewsApiResponse> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pagesize,
            @Query("apikey") String apikey
    );
}
