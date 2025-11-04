package org.iesch.a03_menu_principal.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

object UserPreferencesManager {
    private val KEY_PREFIX = stringPreferencesKey("user_prefix")
    private val KEY_NAME = stringPreferencesKey("name")
    private val KEY_AGE = stringPreferencesKey("age")
    private val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
    private val KEY_NOTIFICATIONS = booleanPreferencesKey("notifications")

    private fun getUserKey(key: String, email: String): String {
        return "${email}_$key"
    }

    private fun getPreferenceKey(key: String, email: String): Preferences.Key<String> {
        return stringPreferencesKey(getUserKey(key, email))
    }

    suspend fun saveName(context: Context, email: String, name: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[getPreferenceKey("name", email)] = name
        }
    }

    suspend fun saveAge(context: Context, email: String, age: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[getPreferenceKey("age", email)] = age
        }
    }

    suspend fun saveDarkMode(context: Context, email: String, enabled: Boolean) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(getUserKey("dark_mode", email))] = enabled
        }
    }

    suspend fun saveNotifications(context: Context, email: String, enabled: Boolean) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(getUserKey("notifications", email))] = enabled
        }
    }

    fun getNameFlow(context: Context, email: String): Flow<String?> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[getPreferenceKey("name", email)]
        }
    }

    fun getAgeFlow(context: Context, email: String): Flow<String?> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[getPreferenceKey("age", email)]
        }
    }

    fun getDarkModeFlow(context: Context, email: String): Flow<Boolean> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(getUserKey("dark_mode", email))] ?: false
        }
    }

    fun getNotificationsFlow(context: Context, email: String): Flow<Boolean> {
        return context.userPreferencesDataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(getUserKey("notifications", email))] ?: true
        }
    }

    suspend fun clearUserPreferences(context: Context, email: String) {
        context.userPreferencesDataStore.edit { preferences ->
            preferences.asMap().keys
                .filter { it.name.startsWith(email) }
                .forEach { preferences.remove(it) }
        }
    }
}
