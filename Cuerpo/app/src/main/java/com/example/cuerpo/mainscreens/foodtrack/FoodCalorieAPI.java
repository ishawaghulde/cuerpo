package com.example.cuerpo.mainscreens.foodtrack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FoodCalorieAPI {

    @Headers({
            "x-app-id: e25cbb3c",
            "x-app-key: 786b239f59d6b3017d997503fa037a82"
    })
    @GET("search/instant")
    Call<FoodCaloriePost> getFoodCaloriePosts(@Query("query") String food);
}
