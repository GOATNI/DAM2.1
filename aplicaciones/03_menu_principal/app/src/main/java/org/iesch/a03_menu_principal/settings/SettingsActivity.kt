package org.iesch.a03_menu_principal.settings

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivitySettingsBinding

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object {
        private val KEY_DARKMODE = booleanPreferencesKey("darkmode_enabled")
        private val KEY_BLUETOOTH = booleanPreferencesKey("bluetooth_enabled")
        private val KEY_VIBRATION = booleanPreferencesKey("vibration_enabled")

        // Método estático para aplicar el tema desde cualquier actividad
        suspend fun applyTheme(context: Context) {
            val preferences = context.settingsDataStore.data.first()
            val isDarkMode = preferences[KEY_DARKMODE] ?: false
            val mode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            AppCompatDelegate.setDefaultNightMode(mode)
        }

        // Función para limpiar todas las configuraciones
        suspend fun clearSettings(context: Context) {
            context.settingsDataStore.edit { preferences ->
                preferences.clear()
            }
            // Restaurar modo claro por defecto
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema antes de crear la vista
        lifecycleScope.launch {
            applyTheme(this@SettingsActivity)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar preferencias guardadas
        lifecycleScope.launch {
            val preferences = settingsDataStore.data.first()
            binding.swDarkmode.isChecked = preferences[KEY_DARKMODE] ?: false
            binding.swBluetooth.isChecked = preferences[KEY_BLUETOOTH] ?: false
            binding.swVibracion.isChecked = preferences[KEY_VIBRATION] ?: true
        }

        initUI()
    }

    private fun initUI() {
        binding.rsVolumen.addOnChangeListener { _, value, _ ->
            // Manejo del volumen...
        }

        binding.swDarkmode.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingsDataStore.edit { preferences ->
                    preferences[KEY_DARKMODE] = isChecked
                }
                applyTheme(this@SettingsActivity)
                recreate()
            }
        }

        binding.swBluetooth.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingsDataStore.edit { preferences ->
                    preferences[KEY_BLUETOOTH] = isChecked
                }
            }
        }

        binding.swVibracion.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                settingsDataStore.edit { preferences ->
                    preferences[KEY_VIBRATION] = isChecked
                }
            }
        }
    }
}