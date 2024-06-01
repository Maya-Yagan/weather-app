package com.maya2002yagan.weatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * This data class stores the weather info for the next 7 days.
 * Each field is in the form of a list because the API sends this data as a list for the next 7 days
 *
 * @param time List of strings representing the time of the fetched weather data for each day.
 * @param weather_code List of integers representing the weather code used for displaying weather icons for each day.
 * @param temperature_2m_max List of doubles representing the maximum temperature at 2 meters above ground level for each day. Unit: Celsius (°C).
 * @param temperature_2m_min List of doubles representing the minimum temperature at 2 meters above ground level for each day. Unit: Celsius (°C).
 * @param uv_index_clear_sky_max List of doubles representing the maximum UV index under clear sky conditions for each day.
 * @param rain_sum List of doubles representing the sum of rain for each day. Unit: Millimeters (mm).
 * @param precipitation_probability_max List of integers representing the maximum precipitation probability for each day. Unit: Percentage (%).
 * @param wind_speed_10m_max List of doubles representing the maximum wind speed at 10 meters above ground level for each day. Unit: Kilometers per hour (km/h).
 */
@Parcelize
data class DailyWeather(
    val time: List<String>, //the time of the fetched weather data
    val weather_code: List<Int>, //this code will be used for displaying weather icons
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val uv_index_clear_sky_max: List<Double>,
    val rain_sum: List<Double>,
    val precipitation_probability_max: List<Int>,
    val wind_speed_10m_max: List<Double>,
) : Parcelable
