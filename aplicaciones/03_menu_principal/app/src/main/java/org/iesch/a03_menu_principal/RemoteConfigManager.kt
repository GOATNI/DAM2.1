package org.iesch.a03_menu_principal.config

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.tasks.await
import org.iesch.a03_menu_principal.R

object RemoteConfigManager {
    private const val TAG = "RemoteConfigManager"

    // Keys de Remote Config
    private const val KEY_LOGIN_THEME = "login_theme"

    // Valores posibles para el tema
    const val THEME_BLUE = "blue"
    const val THEME_GREEN = "green"
    const val THEME_PURPLE = "purple"

    private val remoteConfig: FirebaseRemoteConfig by lazy {
        FirebaseRemoteConfig.getInstance().apply {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600 // 1 hora (para producción)
                // Para testing usa: minimumFetchIntervalInSeconds = 0
            }
            setConfigSettingsAsync(configSettings)

            // Valores por defecto
            setDefaultsAsync(
                mapOf(
                    KEY_LOGIN_THEME to THEME_BLUE
                )
            )
        }
    }

    /**
     * Inicializa y obtiene la configuración remota
     */
    suspend fun initialize() {
        try {
            Log.d(TAG, "Obteniendo configuración remota...")
            remoteConfig.fetchAndActivate().await()
            Log.d(TAG, "Configuración remota activada exitosamente")
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener configuración remota", e)
        }
    }

    /**
     * Obtiene el tema actual configurado remotamente
     */
    fun getLoginTheme(): String {
        val theme = remoteConfig.getString(KEY_LOGIN_THEME)
        Log.d(TAG, "Tema obtenido: $theme")
        return theme
    }

    /**
     * Convierte el nombre del tema a su recurso de estilo correspondiente
     */
    fun getThemeResourceId(theme: String): Int {
        return when (theme.lowercase()) {
            THEME_BLUE -> R.style.Theme_Login_Blue
            THEME_GREEN -> R.style.Theme_Login_Green
            THEME_PURPLE -> R.style.Theme_Login_Purple
            else -> R.style.Theme_Login_Blue // Default
        }
    }

    /**
     * Método de conveniencia para obtener directamente el resource ID del tema
     */
    fun getCurrentThemeResourceId(): Int {
        val theme = getLoginTheme()
        return getThemeResourceId(theme)
    }
}