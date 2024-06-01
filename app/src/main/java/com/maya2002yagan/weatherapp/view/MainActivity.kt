package com.maya2002yagan.weatherapp.view

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.maya2002yagan.weatherapp.R
import android.Manifest
import android.text.TextUtils
import android.util.Log
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.ads.HwAds
import com.huawei.hms.common.ApiException

/**
 * MainActivity serves as the entry point of the WeatherApp
 * It handles edge-to-edge UI, permission requests, and initialization of Huawei Ads and Push service
 */
class MainActivity : AppCompatActivity() {
    // Companion object to hold a reference to the activity instance
    companion object{
        lateinit var activity: MainActivity
    }

    // Tag for logging Huawei Push events
    private val TAG = "HUAWEI_PUSH"

    /**
     * Called when the activity is first created
     * Sets up the UI, requests necessary permissions, and initializes Huawei services.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestPermissions()
        activity = this
        getToken()
        // Initialize the Petal Ads SDK.
        HwAds.init(this)
    }

    /**
     * Requests necessary permissions dynamically based on the Android version.
     */
    private fun requestPermissions(){
        // Dynamically apply for required permissions if the API level is 28 or smaller.
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            //Log.i(TAG, "android sdk <= 28 Q")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val strings = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                ActivityCompat.requestPermissions(this, strings, 1)
            }
        } else {
            // Dynamically apply for required permissions if the API level is greater than 28. The android.permission.ACCESS_BACKGROUND_LOCATION permission is required.
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val strings = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
                ActivityCompat.requestPermissions(this, strings, 2)
            }
        }
    }

    /**
     * Retrieves a push token for Huawei Mobile Services (HMS) and logs it
     * The token is used for push notifications
     */
    private fun getToken() {
        // Create a thread.
        object : Thread() {
            override fun run() {
                try {
                    // Obtain the app ID from the agconnect-services.json file.
                    val appId = "111103003"

                    // Set tokenScope to HCM.
                    val tokenScope = "HCM"
                    val token = HmsInstanceId.getInstance(this@MainActivity).getToken(appId, tokenScope)
                    Log.i(TAG, "get token:$token")

                    // Check whether the token is null.
                    if (!TextUtils.isEmpty(token)) {
                        sendRegTokenToServer(token)
                    }
                } catch (e: ApiException) {
                    Log.e(TAG, "get token failed, $e")
                }
            }
        }.start()
    }

    /**
     * Sends the registration token to the server
     * @param token The registration token to be sent
     */
    private fun sendRegTokenToServer(token: String) {
        Log.i(TAG, "sending token to server. token:$token")
    }
}