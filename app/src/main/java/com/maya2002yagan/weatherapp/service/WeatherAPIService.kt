package com.maya2002yagan.weatherapp.service

import com.maya2002yagan.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class encapsulates the setup and interaction with the open-meteo API using Retrofit.
 * It sets up the base URL and Retrofit instance with a JSON converter (Gson).
 * It provides the method getData to fetch weather data for a given latitude and longitude.
 */

class WeatherAPIService {
    /**
     * The property baseURL stores the base url (first part of the API url) which will be used in
     * the property api
     */
    private val baseURL = "https://api.open-meteo.com/"
    /** The property api initializes a Retrofit instance configured with the base URL and a Gson
     * converter for JSON
     */
    private val api = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(WeatherAPI::class.java)

    /**
     * This method retrieves weather data by making an asynchronous API call to the specified
     * endpoint using the provided latitude and longitude parameters.
     *
     *  @param latitude The latitude of the location for which to fetch weather data.
     *  @param longitude The longitude of the location for which to fetch weather data.
     *  @return A Call object that will return a WeatherResponse when executed.
     */
    fun getData(latitude: String, longitude: String) : Call<WeatherResponse>{
        return api.getWeather(latitude, longitude)
    }
}
