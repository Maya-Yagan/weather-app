package com.maya2002yagan.weatherapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maya2002yagan.weatherapp.database.WeatherDao
import com.maya2002yagan.weatherapp.database.WeatherDatabase
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.util.convertToSingleDailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse
import com.maya2002yagan.weatherapp.service.WeatherAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * ViewModel class for managing weather data and interactions with the Weather API and local database.
 * @param application The application instance.
 */
class MainViewModel(application : Application) : AndroidViewModel(application) {
    // Instance of the Weather API service
    private val api = WeatherAPIService()

    // LiveData for holding weather response data
    val weatherData = MutableLiveData<WeatherResponse>()

    // LiveData for indicating if there was an error fetching weather data
    val weatherError = MutableLiveData<Boolean>()

    // LiveData for indicating if weather data is currently loading
    val weatherLoading = MutableLiveData<Boolean>()

    // Instance of the WeatherDatabase
    private var weatherDatabase : WeatherDatabase? = null

    // Data Access Object for interacting with the weather database
    private var weatherDao : WeatherDao? = null

    /**
     * Initializes the ViewModel by setting up the weather database and DAO.
     */
    init {
        weatherDatabase = WeatherDatabase.getInstance(application)
        weatherDao = weatherDatabase?.weatherDao()
    }

    /**
     * Fetches weather data from the API based on the provided latitude and longitude.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     */
    fun getDataFromAPI(latitude: String, longitude: String){
        weatherLoading.value = true
        api.getData(latitude, longitude).enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                weatherData.value = response.body()
                weatherError.value = false
                weatherLoading.value = false
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherLoading.value = false
                weatherError.value = true
                Log.e("RetrofitError", t.message.toString())
            }
        })
    }

    /**
     * Inserts daily weather data into the local database.
     * @param dailyWeather The daily weather data to be inserted.
     */
    fun insert(dailyWeather: DailyWeather) = viewModelScope.launch {
        val singleDailyWeatherList = convertToSingleDailyWeather(dailyWeather)
        singleDailyWeatherList.forEach { singleDailyWeather ->
            weatherDao?.insert(singleDailyWeather)
        }
    }
}
