package com.example.practica04.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.model.Pegi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class AddGameFragmentViewModel() : ViewModel() {

    private val name = MutableLiveData<String>()
    private val studio = MutableLiveData<String>()
    private val date = MutableLiveData<String>()
    private val cover = MutableLiveData<String>()
    private val saveGameValid = MutableLiveData<Boolean>(false)

    fun getName(): LiveData<String> {
        return name
    }

    fun getStudio(): LiveData<String> {
        return studio
    }

    fun getDate(): LiveData<String> {
        return date
    }

    fun getCover(): LiveData<String> {
        return cover
    }

    fun getSaveGameValid(): LiveData<Boolean> {
        return saveGameValid
    }

    fun setName(nameIn: String) {
        name.postValue(nameIn)
    }

    fun setStudio(studioIn: String) {
        studio.postValue(studioIn)
    }

    fun setDate(dateIn: String) {
        date.postValue(dateIn)
    }

    fun setCover(coverIn: String) {
        cover.postValue(coverIn)
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

    fun generateGame(): GameBo? {
        val nameValue = name.value
        val studioValue = studio.value
        val dateValue = date.value
        val coverValue = cover.value


        if (nameValue.isNullOrEmpty() || studioValue.isNullOrEmpty() ||
            dateValue.isNullOrEmpty() || coverValue.isNullOrEmpty()
        ) {
            return null
        }

        val launchDate = try {
            dateValue.toInt()
        } catch (e: NumberFormatException) {
            return null
        }

        val compatiblePlatforms = listOf(CompatiblePlatform.ALL)

        val pegi = Pegi.PEGI3

        return GameBo(0, nameValue, studioValue, launchDate, compatiblePlatforms, pegi, coverValue)
    }
}