package com.example.prepexamen

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property to create DataStore instance
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

// Repository to manage app preferences
class PreferencesRepository(private val context: Context) {

    // Define preference keys
    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        val NOTIFICATION_KEY = booleanPreferencesKey("notifications_enabled")
        val USERNAME_KEY = stringPreferencesKey("username")
    }

    // Read dark mode preference as Flow
    val darkModeFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }

    // Save dark mode preference
    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }

    // Read notification preference
    val notificationFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[NOTIFICATION_KEY] ?: true
        }

    // Save notification preference
    suspend fun setNotifications(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATION_KEY] = enabled
        }
    }
}