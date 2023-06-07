package com.example.practica04.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica04.data.repository.GamesRepository
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.model.Pegi
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddGameFragmentViewModel() : ViewModel() {

    private val repository: GamesRepository by lazy { GamesRepository }
    private val pegiSelected = MutableLiveData<Pegi>()
    private val nintendoSelected = MutableLiveData<Boolean>(false)
    private val playStationSelected = MutableLiveData<Boolean>(false)
    private val xboxSelected = MutableLiveData<Boolean>(false)
    private val newGame = MutableLiveData<GameBo?>()

    fun getNintendoSelected(): LiveData<Boolean> {
        return nintendoSelected
    }

    fun getPlayStationSelected(): LiveData<Boolean> {
        return playStationSelected
    }

    fun getXboxSelected(): LiveData<Boolean> {
        return xboxSelected
    }

    fun getNewGame(): LiveData<GameBo?> {
        return newGame
    }

    fun getPegi(): LiveData<Pegi> {
        return pegiSelected
    }

    fun setNintendoCompatible() {
        nintendoSelected.value = nintendoSelected.value != true
    }

    fun setPlayStationCompatible() {
        playStationSelected.value = playStationSelected.value != true
    }

    fun setXboxCompatible() {
        xboxSelected.value = xboxSelected.value != true
    }

    fun setPegi(pegi: Pegi) {
        pegiSelected.postValue(pegi)
    }

    fun isValidDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false

        return try {
            dateFormat.parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }

    private fun listCompatible(): List<CompatiblePlatform> {
        val compatibleList = mutableListOf<CompatiblePlatform>()
        if (nintendoSelected.value == true) {
            compatibleList.add(CompatiblePlatform.NINTENDO)
        }

        if (playStationSelected.value == true) {
            compatibleList.add(CompatiblePlatform.PLAYSTATION)
        }
        if (xboxSelected.value == true) {
            compatibleList.add(CompatiblePlatform.XBOX)
        }
        return compatibleList
    }

    fun generateGame(name: String, studio: String, date: String, cover: String): GameBo? {
        if (name.isEmpty() || studio.isEmpty() || date.isEmpty() || cover.isEmpty()) {
            return null
        }

        val defaultDate: Date = Calendar.getInstance().apply {
            set(Calendar.YEAR, 1992)
            set(Calendar.MONTH, Calendar.OCTOBER)
            set(Calendar.DAY_OF_MONTH, 10)
        }.time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val releaseDate: Date? = try {
            dateFormat.parse(date)
        } catch (e: ParseException) {
             return null
        }
        val compatiblePlatforms = listCompatible()
        val pegi = pegiSelected.value

        val gameGenerated = GameBo(
            0,
            name,
            studio,
            releaseDate ?: defaultDate,
            compatiblePlatforms,
            pegi ?: Pegi.PEGI16,
            cover
        )

        updateNewGame(gameGenerated)

        return gameGenerated
    }

    private fun updateNewGame(game: GameBo) {
        newGame.value = game
    }

    fun addGameRepository() {
        viewModelScope.launch {
            newGame.value?.let { repository.addGame(it) }
        }
    }

    fun resetNewGame() {
        newGame.value = null
    }
}