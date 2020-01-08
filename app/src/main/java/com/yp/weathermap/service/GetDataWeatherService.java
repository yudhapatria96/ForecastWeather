package com.yp.weathermap.service;

import com.yp.weathermap.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataWeatherService {
    @GET("data/2.5/forecast")
    Call<WeatherResponse> getWeather(@Query("zip") String zip, @Query("appid") String appId);
}
