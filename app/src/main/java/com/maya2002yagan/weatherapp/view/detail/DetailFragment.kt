package com.maya2002yagan.weatherapp.view.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.huawei.hms.ads.AdListener
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.BannerAdSize
import com.huawei.hms.ads.banner.BannerView
import com.maya2002yagan.weatherapp.R
import com.maya2002yagan.weatherapp.databinding.FragmentDetailBinding
import com.maya2002yagan.weatherapp.model.DailyWeather
import com.maya2002yagan.weatherapp.util.ApplicationViewModelFactory
import com.maya2002yagan.weatherapp.util.getWeatherIcon
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

/**
 * A fragment that displays detailed weather information
 * This fragment includes weather data, a loading indicator, and a banner ad
 */
class DetailFragment : Fragment() {
    // Arguments passed to the fragment via navigation
    val args: DetailFragmentArgs by navArgs()

    // Banner ad view from Huawei Ads SDK
    lateinit var bannerView: BannerView

    // Binding object for the fragment layout
    private lateinit var binding : FragmentDetailBinding

    // Tag for logging Huawei Ads events
    private val TAG = "HUAWEI_ADS"

    // Default latitude and longitude used if the actual location cannot be obtained
    private var defaultLatitude = "82.8628"
    private var defaultLongitude = "135.0000"

    // ViewModel for managing UI-related data
    private val viewModel : MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }

    /**
     * Called to have the fragment instantiate its user interface view
     * Inflates the layout, loads the banner ad, fetches weather data, and initializes the UI
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        loadBannerAd()
        viewModel.getDataFromAPI(defaultLatitude, defaultLongitude)
        val selectedDayIndex = args.selectedDayIndex
        val dailyWeather = args.dailyWeather
        initUI(selectedDayIndex, dailyWeather)
        return binding.root
    }

    /**
     * Initializes the user interface and observes ViewModel data for updates.
     */
    private fun initUI(index: Int, dailyWeather: DailyWeather){
        viewModel.weatherData.observe(viewLifecycleOwner){ weather ->
            weather?.daily?.let {
                with(binding){
                    tvMaxTemperatureValue.text = dailyWeather.temperature_2m_max[index].toString() + weather.daily_units.temperature_2m_max
                    tvMinTemperatureValue.text = dailyWeather.temperature_2m_min[index].toString() + weather.daily_units.temperature_2m_min
                    tvRainSumValue.text = dailyWeather.rain_sum[index].toString() + weather.daily_units.rain_sum
                    tvMaxWindSpeedValue.text = dailyWeather.wind_speed_10m_max[index].toString() + weather.daily_units.wind_speed_10m_max
                    tvUVIndexValue.text = dailyWeather.uv_index_clear_sky_max[index].toString()
                    tvMaxPrecipitationProbValue.text = dailyWeather.precipitation_probability_max[index].toString() + weather.daily_units.precipitation_probability_max
                    ivWeatherImageDetail.setImageResource(getWeatherIcon(dailyWeather.weather_code[index], 1, requireContext()))
                }
            }
        }

        viewModel.weatherLoading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                binding.pbLoading.visibility = View.VISIBLE
                binding.ivWeatherImageDetail.visibility = View.GONE
                binding.cvWeatherDetailsCard.visibility = View.GONE
            }
            else{
                binding.pbLoading.visibility = View.GONE
                binding.ivWeatherImageDetail.visibility= View.VISIBLE
                binding.cvWeatherDetailsCard.visibility = View.VISIBLE
            }
        }
    }

    /**
     * Loads a banner ad using Huawei Ads SDK
     */
    private fun loadBannerAd(){
        // Obtain BannerView.
        bannerView = binding.hwBannerView
        bannerView.adListener = getAdListener()
        // Set the ad unit ID and ad dimensions. "testw6vs28auh3" is a dedicated test ad unit ID.
        bannerView.adId = "testw6vs28auh3"
        bannerView.bannerAdSize = BannerAdSize.BANNER_SIZE_360_57
        // Set the refresh interval to 60 seconds.
        bannerView.setBannerRefresh(60)
        // Create an ad request to load an ad.
        val adParam = AdParam.Builder().build()
        bannerView.loadAd(adParam)
    }

    /**
     * Returns an AdListener to handle ad events
     * @return An instance of AdListener
     */
    private fun getAdListener(): AdListener {
        val adListener: AdListener = object : AdListener() {
            override fun onAdLoaded() {
                // Called when an ad is loaded successfully.
                Log.i(TAG,"onAdLoaded")
            }
            override fun onAdFailed(errorCode: Int) {
                // Called when an ad fails to be loaded.
                Log.i(TAG,"onAdFailed")
                Log.i(TAG,errorCode.toString())
            }
            override fun onAdOpened() {
                // Called when an ad is opened.
                Log.i(TAG,"onAdOpened")
            }
            override fun onAdClicked() {
                // Called when an ad is clicked.
                Log.i(TAG,"onAdClicked")
            }
            override fun onAdLeave() {
                // Called when an ad leaves an app.
                Log.i(TAG,"onAdLeave")
            }
            override fun onAdClosed() {
                // Called when an ad is closed.
                Log.i(TAG,"onAdClosed")
            }
        }
        return adListener
    }

    /**
     * Called when the fragment is being destroyed
     * Destroys the banner ad to release resources
     */
    override fun onDestroy() {
        super.onDestroy()
        bannerView.destroy()
    }
}