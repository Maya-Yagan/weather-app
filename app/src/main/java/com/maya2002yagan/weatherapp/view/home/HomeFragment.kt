package com.maya2002yagan.weatherapp.view.home

import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.huawei.hms.location.FusedLocationProviderClient
import com.huawei.hms.location.LocationCallback
import com.huawei.hms.location.LocationRequest
import com.huawei.hms.location.LocationResult
import com.huawei.hms.location.LocationServices
import com.maya2002yagan.weatherapp.R
import com.maya2002yagan.weatherapp.adapter.WeatherAdapter
import com.maya2002yagan.weatherapp.databinding.FragmentHomeBinding
import com.maya2002yagan.weatherapp.util.ApplicationViewModelFactory
import com.maya2002yagan.weatherapp.util.getWeatherIcon
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

/**
 * HomeFragment is the main fragment that displays the weather information.
 * It handles location services, sets up the weather data observer, and initializes the UI components.
 */
class HomeFragment : Fragment() {
    // Fused location provider client to get the user's location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    // Data binding for the HomeFragment layout
    private lateinit var binding: FragmentHomeBinding

    // Default latitude and longitude used if the actual location cannot be obtained
    private var defaultLatitude = "82.8628"
    private var defaultLongitude = "135.0000"

    // ViewModel for accessing weather data
    private val viewModel: MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }

    /**
     * Called to have the fragment instantiate its user interface view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.rvWeatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        initLocation()
        setObservers()
        return binding.root
    }

    /**
     * Sets up observers for weather data, loading status, and error messages.
     * Updates the UI based on changes in these observables
     */
    private fun setObservers() {
        viewModel.weatherData.observe(viewLifecycleOwner) { list ->
            val adapter = WeatherAdapter(requireContext(), mutableListOf()) { position ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(position, list.daily)
                findNavController().navigate(action)
            }
            binding.rvWeatherRecyclerView.adapter = adapter
            list?.let {
                adapter.updateList(it)
                binding.ivWeatherImage.setImageResource(getWeatherIcon(it.current.weather_code, it.current.is_day, requireContext()))
                binding.tvTemperature.text = "${it.current.temperature_2m}${it.current_units.temperature_2m}"
                binding.tvTimeZone.text = it.timezone
                binding.tvCloudCover.text = "${it.current.cloud_cover}${it.current_units.cloud_cover}"
                binding.tvApparentTemperature.text = "${it.current.apparent_temperature}${it.current_units.apparent_temperature}"
                binding.tvHumidity.text = "${it.current.relative_humidity_2m}${it.current_units.relative_humidity_2m}"
                binding.tvWindDirection.text = "${it.current.wind_direction_10m}${it.current_units.wind_direction_10m}"
                binding.tvWindSpeed.text = "${it.current.wind_speed_10m}${it.current_units.wind_speed_10m}"
            }
            viewModel.insert(list.daily)
        }

        viewModel.weatherLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.pbLoading.visibility = View.VISIBLE
                binding.ivWeatherImage.visibility = View.GONE
                binding.tvTimeZone.visibility = View.GONE
                binding.cvWeatherDetailsCard.visibility = View.GONE
                binding.tvWeatherTodayText.visibility = View.GONE
            }
            else {
                binding.pbLoading.visibility = View.GONE
                binding.ivWeatherImage.visibility = View.VISIBLE
                binding.tvTimeZone.visibility = View.VISIBLE
                binding.cvWeatherDetailsCard.visibility = View.VISIBLE
                binding.tvWeatherTodayText.visibility = View.VISIBLE
            }
        }

        viewModel.weatherError.observe(viewLifecycleOwner) { error ->
            if (error)
                binding.tvError.visibility = View.VISIBLE
            else
                binding.tvError.visibility = View.GONE
        }
    }

    /**
     * Initializes the location services to get the user's current location
     * Fetches weather data based on the obtained location or uses default location if the actual location is not available
     */
    private fun initLocation(){
        // Instantiate the fusedLocationProviderClient object.
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val mLocationRequest = LocationRequest()
        val mLocationCallback: LocationCallback
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult != null) {
                    // TODO: Process the location callback result.
                }
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper()).addOnSuccessListener{
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {location ->
                location?.let {
                    val latitude = it.latitude.toString()
                    val longitude = it.longitude.toString()
                    Toast.makeText(requireContext(), "$latitude\n$longitude", Toast.LENGTH_LONG).show()
                    viewModel.getDataFromAPI(latitude, longitude) // Fetch data with actual location
                }?: run {
                    // If the location is null, fetch data with the default location
                    viewModel.getDataFromAPI(defaultLatitude, defaultLongitude)
                }
            }.addOnFailureListener {exception ->
                Log.e("ERORR-e", exception.toString())
            }
        }.addOnFailureListener{exception ->
            Log.e("ERORR-e", exception.toString())
        }
    }
}
