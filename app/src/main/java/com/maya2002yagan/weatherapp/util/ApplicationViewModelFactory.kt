package com.maya2002yagan.weatherapp.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maya2002yagan.weatherapp.viewmodel.MainViewModel

/**
 * This factory class is used to create an instance of MainViewModel, passing the Application context
 * to its constructor. It ensures that the ViewModel is provided with the necessary
 * application context for its operations.
 *
 * @param application The Application instance used to create the ViewModel.
 */
class ApplicationViewModelFactory(private val application : Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}