package com.maya2002yagan.weatherapp.model

/**
 * This class stores the weather units for CurrentWeather class
 *
 * @param time The unit for time, typically in ISO 8601 format.
 * @param interval The unit for time intervals, typically in seconds.
 * @param temperature_2m The unit for temperature measured at 2 meters above ground level, typically in degrees Celsius (°C).
 * @param relative_humidity_2m The unit for relative humidity measured at 2 meters above ground level, typically in percentage (%).
 * @param apparent_temperature The unit for apparent temperature, typically in degrees Celsius (°C).
 * @param weather_code The unit for weather conditions, typically using WMO codes.
 * @param cloud_cover The unit for cloud cover, typically in percentage (%).
 * @param wind_speed_10m The unit for wind speed measured at 10 meters above ground level, typically in kilometers per hour (km/h).
 * @param wind_direction_10m The unit for wind direction measured at 10 meters above ground level, typically in degrees (°).
 */
data class WeatherUnits(
    val time: String, // unit: iso8601
    val interval: String, // unit: seconds
    val temperature_2m: String, // unit: °C
    val relative_humidity_2m: String, // unit: %
    val apparent_temperature: String, // unit: °C
    val weather_code: String, // unit: wmo code
    val cloud_cover: String, // unit: %
    val wind_speed_10m: String, // unit: km/h
    val wind_direction_10m: String, // unit: °
)

