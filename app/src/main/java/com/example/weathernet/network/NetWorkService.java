package com.example.weathernet.network;

import com.example.weathernet.WeatherResult;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetWorkService {
    public static final String BASE_URL = "http://www.weather.com.cn/";

    @GET("weather/101270101.shtml")
    Observable<WeatherResult> getNowDayWeather();
}
