package com.example.practica04.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherFragmentViewModel : ViewModel() {


    private val navigationStart: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        value = false
    }
    private val delayMillis: Long = 2000

    fun getNavigationStart(): LiveData<Boolean> {
        return navigationStart
    }

    fun navigateWithDelay() {
        viewModelScope.launch {
            delay(delayMillis)
            navigationStart.postValue(true)
        }
    }
}