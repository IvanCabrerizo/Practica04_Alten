package com.example.practica04.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.practica04.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherFragmentViewModel : ViewModel() {

    private val delayMillis: Long = 2000

    fun navigateWithDelay(navController: NavController) {
        viewModelScope.launch {
            delay(delayMillis)
            navController.navigate(R.id.action_launcherFragment_to_gamesFragment)
        }
    }
}