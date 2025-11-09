package org.iesch.a03_menu_principal.datastore

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
private val Context.loginDataStore: DataStore<Preferences> by preferencesDataStore(name = "loginDB")

object LoginDataStore {
    private val KEY_EMAIL = stringPreferencesKey("email")
    private val KEY_PASSWORD = stringPreferencesKey("password")
    private val KEY_PROVIDER = stringPreferencesKey("provider")
    private val KEY_LOGGED = booleanPreferencesKey("logged_in")

    /**
     * Guarda las credenciales del usuario y el proveedor de autenticación
     * @param context Contexto de la aplicación
     * @param email Email del usuario
     * @param password Contraseña (vacío para Google login)
     * @param provider Tipo de proveedor (EMAILYCONTRASENA o GOOGLE)
     */
    suspend fun saveCredentials(
        context: Context,
        email: String,
        password: String,
        provider: String
    ) {
        context.loginDataStore.edit { prefs ->
            prefs[KEY_EMAIL] = email
            prefs[KEY_PASSWORD] = password
            prefs[KEY_PROVIDER] = provider
            prefs[KEY_LOGGED] = true
        }
    }

    /**
     * Verifica si hay una sesión activa
     */
    fun isLoggedFlow(context: Context): Flow<Boolean> =
        context.loginDataStore.data.map { prefs ->
            prefs[KEY_LOGGED] ?: false
        }

    /**
     * Obtiene el email del usuario logueado
     */
    fun getEmailFlow(context: Context): Flow<String?> =
        context.loginDataStore.data.map { prefs ->
            prefs[KEY_EMAIL]
        }

    /**
     * Obtiene el proveedor de autenticación
     */
    fun getProviderFlow(context: Context): Flow<String?> =
        context.loginDataStore.data.map { prefs ->
            prefs[KEY_PROVIDER]
        }

    /**
     * Limpia solo las credenciales de login
     * IMPORTANTE: No toca las preferencias del usuario (tema, idioma, etc.)
     * que están en UserPreferencesManager
     */
    suspend fun clearCredentials(context: Context) {
        context.loginDataStore.edit { prefs ->
            prefs.remove(KEY_EMAIL)
            prefs.remove(KEY_PASSWORD)
            prefs.remove(KEY_PROVIDER)
            prefs[KEY_LOGGED] = false
        }
    }
}