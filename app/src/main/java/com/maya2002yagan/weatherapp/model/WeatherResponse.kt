package com.maya2002yagan.weatherapp.model

/**
 * This class is basically what is sent in the API response.
 *
 * @param latitude The latitude of the user for location-based services.
 * @param longitude The longitude of the user for location-based services.
 * @param current_units The weather units used for the current weather.
 * @param current The current weather conditions.
 * @param daily_units The weather units used for the daily forecast.
 * @param daily The daily weather forecast for the next few days.
 * @param timezone The timezone of the user.
 */
data class WeatherResponse(
    val latitude: Double, //the latitude of the user (used for providing location-based service)
    val longitude: Double, //the longitude of the user (used for providing location-based service)
    val current_units: WeatherUnits, //the weather units for the class CurrentWeather
    val current: CurrentWeather,
    val daily_units: DailyUnits, //the weather units for the class DailytWeather
    var daily: DailyWeather,
    val timezone: String //the time zone of the user
)
