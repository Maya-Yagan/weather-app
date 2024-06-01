package com.maya2002yagan.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maya2002yagan.weatherapp.databinding.ItemWeatherBinding
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.model.WeatherResponse
import com.maya2002yagan.weatherapp.util.convertToDailyWeather
import com.maya2002yagan.weatherapp.util.getWeatherIcon

/**
 * Adapter class for managing and binding daily weather data to the RecyclerView
 * @param context The context in which the adapter is operating
 * @param list The list of daily weather data to be displayed
 * @param onClick A lambda function to handle item clicks, providing the position of the clicked item
 */
class WeatherAdapter(private val context : Context, private val list : MutableList<DailyWeather>, private val onClick : (position : Int) -> Unit) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    /**
     * ViewHolder class for the weather item view
     * @param binding The view binding for the weather item layout
     */
    inner class WeatherViewHolder(private val binding : ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds the daily weather data to the view components
         * @param dailyWeather The daily weather data for the given position
         * @param position The position of the item in the list
         */
        fun bind(dailyWeather: DailyWeather, position : Int){
            binding.tvMaxTemperature.text = dailyWeather.temperature_2m_max[position].toString() + "°C"
            binding.tvMinTemperature.text = dailyWeather.temperature_2m_min[position].toString() + "°C"
            binding.tvDate.text = dailyWeather.time[position]
            binding.ivWeatherIcon.setImageResource(getWeatherIcon(dailyWeather.weather_code[position], 1, context))
            binding.cardView.setOnClickListener {
                onClick(position)
            }
        }
    }

    /**
     * Inflates the item view and creates a ViewHolder instance
     * @param parent The parent view group
     * @param viewType The view type of the new view
     * @return A new WeatherViewHolder instance
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    /**
     * Returns the total number of items in the data set held by the adapter
     * @return The number of items in the list
     */
    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    /**
     * Binds the data to the view holder at the specified position
     * @param holder The view holder
     * @param position The position of the item in the list
     */
    fun updateList(newList: WeatherResponse){
        list.clear()
        val dailyWeather = convertToDailyWeather(newList)
        list.addAll(dailyWeather)
        notifyDataSetChanged()
    }
}