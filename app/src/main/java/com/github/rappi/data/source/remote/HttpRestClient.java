package com.github.rappi.data.source.remote;

import com.github.rappi.data.Theme;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by Roger Patiño on 06/01/2016.
 */
public interface HttpRestClient {

    public static String BASE_URL = "https://www.reddit.com/";

    @Headers("ContentType: application/json")
    @GET("reddits.json")
    Call<Theme> performTheme();


}