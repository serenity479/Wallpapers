package com.example.wallpapers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.wallpapers.ApiUtilities.API;

public interface ApiInterface {
    
    String BASE_URL = "https://api.pexels.com/v1/";

    @Headers("Authorization: "+API)
    @GET("curated")
    Call<SearchModel> getTrendingImages(
            @Query("page") int page,
            @Query("per_page") int per_page);

    @Headers("Authorization: "+API)
    @GET("search")
    Call<SearchModel> getSearchImages(
            @Query("query") String query ,
            @Query("page") int page,
            @Query("per_page") int per_page);


}
