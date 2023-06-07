package com.example.practica04.data.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "userPreferences")

class UserPreferencesProvider(context: Context) {

    private val SORT_KEY = stringPreferencesKey("sortType")
    private val FILTER_KEY = stringPreferencesKey("filterType")

    private val dataStore = context.dataStore

    fun getFilterSelected(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[FILTER_KEY] ?: "ALL"
        }
    }

    fun getSortSelected(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[SORT_KEY] ?: "ID"
        }
    }

    suspend fun updateFilterSelected(platform: String) {
        dataStore.edit { preferences ->
            preferences[FILTER_KEY] = platform
        }
    }

    suspend fun updateSortSelected(sort: String) {
        dataStore.edit { preferences ->
            preferences[SORT_KEY] = sort
        }
    }
}