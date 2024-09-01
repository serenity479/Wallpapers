package com.example.wallpapers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {


    private static Retrofit retrofit = null;
    public static final String API="563492ad6f91700001000001306f6e6bec1f43b2b9edc1238aa9a7c1";



    public static ApiInterface getApiInterface(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
