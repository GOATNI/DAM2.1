package org.iesch.a03_menu_principal.settings

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivitySettingsBinding
import org.iesch.a03_menu_principal.datastore.LoginDataStore
import org.iesch.a03_menu_principal.preferences.UserPreferencesManager

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private var currentUserEmail: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema antes de crear la vista
        lifecycleScope.launch {
            currentUserEmail = LoginDataStore.getEmailFlow(applicationContext).first()
            currentUserEmail?.let { email ->
                val isDarkMode = UserPreferencesManager.getDarkModeFlow(applicationContext, email).first()
                val mode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                AppCompatDelegate.setDefaultNightMode(mode)
                delegate.applyDayNight()
            }
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

        // Obtener email del usuario actual y cargar preferencias
        lifecycleScope.launch {
            currentUserEmail = LoginDataStore.getEmailFlow(applicationContext).first()
            loadPreferences()
        }

        initUI()
    }

    private suspend fun loadPreferences() {
        currentUserEmail?.let { email ->
            // Cargar valores guardados del usuario actual
            val savedName = UserPreferencesManager.getNameFlow(applicationContext, email).first() ?: ""
            val savedAge = UserPreferencesManager.getAgeFlow(applicationContext, email).first() ?: ""
            val isDarkMode = UserPreferencesManager.getDarkModeFlow(applicationContext, email).first()
            val notificationsEnabled = UserPreferencesManager.getNotificationsFlow(applicationContext, email).first()

            // Actualizar UI
            binding.editTextName.setText(savedName)
            binding.editTextAge.setText(savedAge)
            binding.switchDarkMode.isChecked = isDarkMode
            binding.switchNotifications.isChecked = notificationsEnabled
        }
    }

    private fun initUI() {
        binding.buttonSaveName.setOnClickListener {
            lifecycleScope.launch {
                currentUserEmail?.let { email ->
                    val name = binding.editTextName.text.toString()
                    UserPreferencesManager.saveName(applicationContext, email, name)
                }
            }
        }

        binding.buttonSaveAge.setOnClickListener {
            lifecycleScope.launch {
                currentUserEmail?.let { email ->
                    val age = binding.editTextAge.text.toString()
                    UserPreferencesManager.saveAge(applicationContext, email, age)
                }
            }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                currentUserEmail?.let { email ->
                    // Guardar en UserPreferencesManager (compartido con UserPreferencesActivity)
                    UserPreferencesManager.saveDarkMode(applicationContext, email, isChecked)

                    // Aplicar tema inmediatamente
                    val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                    AppCompatDelegate.setDefaultNightMode(mode)
                    recreate()
                }
            }
        }

        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                currentUserEmail?.let { email ->
                    // Guardar en UserPreferencesManager (compartido con UserPreferencesActivity)
                    UserPreferencesManager.saveNotifications(applicationContext, email, isChecked)
                }
            }
        }

        binding.buttonVolver.setOnClickListener {
            finish()
        }
    }
}