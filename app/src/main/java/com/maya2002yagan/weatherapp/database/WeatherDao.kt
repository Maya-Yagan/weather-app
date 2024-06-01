package com.maya2002yagan.weatherapp.database

import androidx.room.Dao
import androidx.room.Insert
import com.maya2002yagan.weatherapp.model.SingleDailyWeather

/**
 * The DAO interface is responsible for defining methods for interacting with the database,
 * specifically for performing operations such as inserting, updating, deleting, and querying data.
 * In this project, only the insert operation is needed
 */
@Dao
interface WeatherDao{
    /**
     * Inserts a single daily weather entry into the Room database.
     *
     * This method is used to insert a SingleDailyWeather object into the database.
     * The @Insert annotation indicates that this method performs an insert operation.
     * The method is also marked as "suspend" to support coroutine usage,
     * allowing the operation to be performed asynchronously.
     *
     * @param dailyWeather The `SingleDailyWeather` object to be inserted into the database.
     */
    @Insert
    suspend fun insert(dailyWeather: SingleDailyWeather)
}