package com.maya2002yagan.weatherapp.model

/**
 * This data class stores the current weather info.
 *
 * @param time The time at which the weather data was fetched.
 * @param temperature_2m The actual temperature at 2 meters above the ground.
 * @param relative_humidity_2m The current relative humidity percentage at 2 meters above the ground.
 * @param apparent_temperature The perceived temperature, taking into account factors such as wind chill and humidity.
 * @param weather_code The code representing the current weather condition, used for displaying weather icons.
 * @param cloud_cover The current cloud cover percentage.
 * @param wind_speed_10m The current wind speed at 10 meters above the ground.
 * @param wind_direction_10m The current wind direction at 10 meters above the ground, in degrees.
 * @param is_day An integer indicating whether it is day (1) or night (0), used for determining the appropriate weather icon.
 */
data class CurrentWeather(
    val time: String, //the time of the fetched weather data
    val temperature_2m: Double, //the actual temperature right now
    val relative_humidity_2m: Int, //the humidity now
    val apparent_temperature: Double, //how the temperature feels like
    val weather_code: Int, //this code will be used for displaying weather icons
    val cloud_cover: Int, //the current cloud cover percentage
    val wind_speed_10m: Double,
    val wind_direction_10m: Int,
    val is_day: Int //this is used for determining whether a night or day icon will be displayed
)