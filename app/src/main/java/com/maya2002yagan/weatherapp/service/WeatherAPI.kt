package com.maya2002yagan.weatherapp.service

import com.maya2002yagan.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  This interface is used to fetch the weather data from https://api.open-meteo.com/ using Retrofit
 */
interface WeatherAPI {
    /**
     * The function getWeather is responsible for fetching the data. The annotation @GET makes the
     * request to v1/forecast and the annotation @Query will append the other parameter to the url
     * like latitude and longitude which have to be dynamically set (which is done in HomeFragment)
     *
     * @param latitude The latitude of the location for which to fetch weather data.
     * @param longitude The longitude of the location for which to fetch weather data.
     * @param current A comma-separated list of fields for current weather data (default: "is_day,temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m").
     * @param daily A comma-separated list of fields for daily weather data (default: "weather_code,temperature_2m_max,temperature_2m_min,uv_index_clear_sky_max,rain_sum,precipitation_probability_max,wind_speed_10m_max").
     * @param timezone The timezone for the weather data (default: "auto").
     * @return A Call object for the HTTP request, which will return a WeatherResponse when executed.
     */
    @GET("v1/forecast")
    fun getWeather(
        @Query("latitude") latitude: String, //this will be set later in HomeFragment
        @Query("longitude") longitude: String, //this will be set later in HomeFragment
        @Query("current") current: String = "is_day,temperature_2m,relative_humidity_2m,apparent_temperature,weather_code,cloud_cover,wind_speed_10m,wind_direction_10m",
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min,uv_index_clear_sky_max,rain_sum,precipitation_probability_max,wind_speed_10m_max",
        @Query("timezone") timezone: String = "auto"
    ): Call<WeatherResponse>
}
