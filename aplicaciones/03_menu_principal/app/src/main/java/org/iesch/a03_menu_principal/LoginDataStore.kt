package org.iesch.a03_menu_principal

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Nombre de la "base de datos": loginDB
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "loginDB")

object LoginDataStore {
    private val KEY_EMAIL = stringPreferencesKey("email")
    private val KEY_PASSWORD = stringPreferencesKey("password")
    private val KEY_LOGGED = booleanPreferencesKey("logged_in")

    suspend fun saveCredentials(context: Context, email: String, password: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_EMAIL] = email
            prefs[KEY_PASSWORD] = password
            prefs[KEY_LOGGED] = true
        }
    }

    fun isLoggedFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[KEY_LOGGED] ?: false
        }

    fun getEmailFlow(context: Context): Flow<String?> =
        context.dataStore.data.map { prefs ->
            prefs[KEY_EMAIL]
        }

    suspend fun clearCredentials(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(KEY_EMAIL)
            prefs.remove(KEY_PASSWORD)
            prefs[KEY_LOGGED] = false
        }
    }
}
