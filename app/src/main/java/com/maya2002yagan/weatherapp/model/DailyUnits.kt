package com.maya2002yagan.weatherapp.model

/**
 * This class stores the weather units for DailyWeather class
 *
 * @param time The time of the weather data. Format: ISO 8601.
 * @param weatherCode The weather code. Unit: WMO code.
 * @param maxTemperature2m The maximum temperature at 2 meters above ground level. Unit: Celsius (°C).
 * @param minTemperature2m The minimum temperature at 2 meters above ground level. Unit: Celsius (°C).
 * @param maxUvIndexClearSky The maximum UV index under clear sky conditions.
 * @param rainSum The sum of rain. Unit: Millimeters (mm).
 * @param precipitationProbabilityMax The maximum precipitation probability. Unit: Percentage (%).
 * @param maxWindSpeed10m The maximum wind speed at 10 meters above ground level. Unit: Kilometers per hour (km/h).
 */
data class DailyUnits(
    val time: String, // unit: iso8601
    val weather_code: String, // unit: wmo code
    val temperature_2m_max: String, // unit: °C
    val temperature_2m_min: String, // unit: °C
    val uv_index_clear_sky_max: String, // unit: nothing
    val rain_sum: String, // unit: mm
    val precipitation_probability_max: String, // unit: %
    val wind_speed_10m_max: String, // unit: km/h
)