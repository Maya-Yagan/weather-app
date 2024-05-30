package com.maya2002yagan.weatherapp.util

import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.SingleDailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse

/**
 * This utility function extracts the daily weather data from the WeatherResponse and
 * transforms it into a list of DailyWeather objects, which can then be used for further
 * processing or displaying in the UI.
 *
 * @param weatherResponse The WeatherResponse object containing the weather data.
 * @return A list of DailyWeather objects.
 */
fun convertToDailyWeather(weatherResponse: WeatherResponse): List<DailyWeather> {
    val dailyWeather = mutableListOf<DailyWeather>()
    val daily = weatherResponse.daily
    for (i in daily.time.indices) {
        val entry = DailyWeather(
            time = daily.time,
            weather_code= daily.weather_code,
            temperature_2m_max = daily.temperature_2m_max,
            temperature_2m_min = daily.temperature_2m_min,
            precipitation_probability_max = daily.precipitation_probability_max,
            uv_index_clear_sky_max = daily.uv_index_clear_sky_max,
            rain_sum = daily.rain_sum,
            wind_speed_10m_max = daily.wind_speed_10m_max
        )
        dailyWeather.add(entry)
    }
    return dailyWeather
}

/**
 *  This utility function converts daily weather data into a list of single daily weather objects.
 * @param dailyWeather The daily weather data to be converted.
 * @return A list of SingleDailyWeather objects.
 */
fun convertToSingleDailyWeather(dailyWeather: DailyWeather): List<SingleDailyWeather> {
    val size = dailyWeather.time.size
    val singleDailyWeatherList = mutableListOf<SingleDailyWeather>()
    for (i in 0 until size) {
        val singleDailyWeather = SingleDailyWeather(
            time = dailyWeather.time[i],
            weather_code = dailyWeather.weather_code[i],
            temperature_2m_max = dailyWeather.temperature_2m_max[i],
            temperature_2m_min = dailyWeather.temperature_2m_min[i],
            uv_index_clear_sky_max = dailyWeather.uv_index_clear_sky_max[i],
            rain_sum = dailyWeather.rain_sum[i],
            precipitation_probability_max = dailyWeather.precipitation_probability_max[i],
            wind_speed_10m_max = dailyWeather.wind_speed_10m_max[i]
        )
        singleDailyWeatherList.add(singleDailyWeather)
    }

    return singleDailyWeatherList
}
