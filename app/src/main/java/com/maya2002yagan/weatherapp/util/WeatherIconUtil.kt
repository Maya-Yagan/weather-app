package com.maya2002yagan.weatherapp.util

import android.content.Context
import com.maya2002yagan.weatherapp.R

// A full list of weather codes is found at the end of this file

/**
 * Retrieves the appropriate weather icon resource ID based on the weather code and time of day.
 *
 * This utility function maps a given weather code and time of day (day or night) to a corresponding
 * weather icon resource. The function first determines whether it is day or night, and then selects
 * the appropriate icon resource based on the provided weather code.
 *
 * @param weatherCode The code representing the current weather condition.
 * @param isDay An integer indicating whether it is day (1) or night (0).
 * @param context The context used to access the application's resources.
 * @return The resource ID of the corresponding weather icon.
 */
fun getWeatherIcon(weatherCode: Int, isDay: Int, context: Context) : Int{
    val dayOrNight = if(isDay == 1) "_day" else "_night"
    return when(weatherCode){
        0-> getIconID("clearsky$dayOrNight", context)

        1-> getIconID("fair$dayOrNight", context)
        2-> getIconID("partlycloudy$dayOrNight", context)
        3-> R.drawable.cloudy

        45-> R.drawable.fog
        48-> R.drawable.fog

        51-> R.drawable.lightrain
        53-> R.drawable.rain
        55-> R.drawable.heavyrain

        56-> R.drawable.lightsleet
        57-> R.drawable.heavysleet

        61-> R.drawable.lightrain
        62-> R.drawable.rain
        63-> R.drawable.heavyrain

        66-> R.drawable.lightsleet
        67-> R.drawable.heavysleet

        71-> R.drawable.lightsnow
        72-> R.drawable.snow
        73-> R.drawable.heavysnow

        77-> R.drawable.snow

        80-> getIconID("lightrainshowers$dayOrNight", context)
        81-> getIconID("rainshowers$dayOrNight", context)
        82-> getIconID("heavyrainshowers$dayOrNight", context)

        85-> getIconID("snowshowers$dayOrNight", context)
        86-> getIconID("heavysnowshowers$dayOrNight", context)

        95-> R.drawable.rainandthunder

        96-> R.drawable.lightrainandthunder
        99-> R.drawable.heavyrainandthunder
        else-> R.drawable.ic_launcher_foreground
    }
}

/**
 * Retrieves the resource ID for a weather icon based on its name.
 *
 * This function helps to dynamically obtain the resource ID for a weather icon based on
 * the provided icon name and context. It is used to support the selection of different
 * icons for day and night.
 *
 * @param icon The name of the icon resource.
 * @param context The context used to access the application's resources.
 * @return The resource ID of the specified icon.
 */
fun getIconID(icon : String, context: Context) : Int{
    return context.resources.getIdentifier(icon, "drawable", context.packageName)
}

/**
 * Code 	Description
 * 0 	Clear sky
 * 0: clearsky_day + clearsky_night
 * ===================================
 * 1, 2, 3 	Mainly clear, partly cloudy, and overcast
 * 1: fair_day + fair_night
 * 2: partlycloudy_day + partlycloudy_night
 * 3: cloudy
 * ===================================
 * 45, 48 	Fog and depositing rime fog
 * 45: fog
 * 48: fog
 * ===================================
 * 51, 53, 55 	Drizzle: Light, moderate, and dense intensity
 * 51: lightrain
 * 53: rain
 * 55: heavyrain
 * ===================================
 * 56, 57 	Freezing Drizzle: Light and dense intensity
 * 56: lightsleet
 * 57: heavysleet
 * ===================================
 * 61, 63, 65 	Rain: Slight, moderate and heavy intensity
 * 61: lightrain
 * 62: rain
 * 63: heavyrain
 * ===================================
 * 66, 67 	Freezing Rain: Light and heavy intensity
 * 66: lightsleet
 * 67: heavysleet
 * ===================================
 * 71, 73, 75 	Snow fall: Slight, moderate, and heavy intensity
 * 71: lightsnow
 * 72: snow
 * 73: heavysnow
 * ===================================
 * 77 	Snow grains
 * 77: snow
 * ===================================
 * 80, 81, 82 	Rain showers: Slight, moderate, and violent
 * 80: lightrainshowers_day + lightrainshowers_night
 * 81: rainshowers_day + rainshowers_night
 * 82: heavyrainshowers_day + heavyrainshowers_night
 * ===================================
 * 85, 86 	Snow showers slight and heavy
 * 85: snowshowers_day + snowshowers_night
 * 86: heavysnowshowers_day + heavysnowshowers_night
 * ===================================
 * 95 * 	Thunderstorm: Slight or moderate
 * rainandthunder
 * ===================================
 * 96, 99  	Thunderstorm with slight and heavy hail
 * 96: lightrainandthunder
 * 99: heavyrainandthunder
 * ===================================
 */

