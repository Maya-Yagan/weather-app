package com.maya2002yagan.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maya2002yagan.weatherapp.model.SingleDailyWeather

/**
 * This class represents the Room database for storing weather data.
 *
 * The @Database annotation defines this class as a Room database and specifies the entities
 * it contains and the database version.
 */
@Database(entities = [SingleDailyWeather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    /**
     * Retrieves the Data Access Object (DAO) for accessing weather data.
     *
     * This method returns an instance of the WeatherDao interface, which provides methods
     * for interacting with the weather data stored in the database.
     */
    abstract fun weatherDao(): WeatherDao
    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        /**
         * Retrieves the instance of the WeatherDatabase.
         *
         * This method ensures that only one instance of the WeatherDatabase is created
         * and provides access to that instance throughout the application.
         *
         * @param context The application context used to create or retrieve the database instance.
         * @return The singleton instance of the WeatherDatabase.
         */
        fun getInstance(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}