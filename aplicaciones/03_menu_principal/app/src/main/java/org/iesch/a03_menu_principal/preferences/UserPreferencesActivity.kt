package org.iesch.a03_menu_principal.preferences

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.databinding.ActivityUserPreferencesBinding
import org.iesch.a03_menu_principal.datastore.LoginDataStore

class UserPreferencesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserPreferencesBinding
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
        binding = ActivityUserPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener email del usuario actual
        lifecycleScope.launch {
            currentUserEmail = LoginDataStore.getEmailFlow(applicationContext).first()
            loadSavedPreferences()
            setupUI()
        }
    }

    private fun loadSavedPreferences() {
        lifecycleScope.launch {
            currentUserEmail?.let { email ->
                // Cargar valores guardados específicos del usuario
                val savedName = UserPreferencesManager.getNameFlow(applicationContext, email).first() ?: ""
                val savedAge = UserPreferencesManager.getAgeFlow(applicationContext, email).first() ?: ""
                val isDarkMode = UserPreferencesManager.getDarkModeFlow(applicationContext, email).first()
                val notificationsEnabled = UserPreferencesManager.getNotificationsFlow(applicationContext, email).first()

                // Actualizar UI con valores guardados
                binding.editTextName.setText(savedName)
                binding.editTextAge.setText(savedAge)
                binding.switchDarkMode.isChecked = isDarkMode
                binding.switchNotifications.isChecked = notificationsEnabled

                // Actualizar textos de estado
                updateStatusTexts(savedName, savedAge, isDarkMode, notificationsEnabled)
            }
        }
    }

    private fun updateStatusTexts(name: String, age: String, isDarkMode: Boolean, notificationsEnabled: Boolean) {
        binding.textViewNameStatus.text = "Nombre: ${if (name.isEmpty()) "No establecido" else name}"
        binding.textViewAgeStatus.text = "Edad: ${if (age.isEmpty()) "No establecida" else age}"
        binding.textViewDarkModeStatus.text = "Modo Oscuro: ${if (isDarkMode) "Activado" else "Desactivado"}"
        binding.textViewNotificationsStatus.text = "Notificaciones: ${if (notificationsEnabled) "Activadas" else "Desactivadas"}"
    }

    private fun setupUI() {
        binding.buttonSaveName.setOnClickListener {
            val name = binding.editTextName.text.toString()
            lifecycleScope.launch {
                currentUserEmail?.let { email ->
                    UserPreferencesManager.saveName(applicationContext, email, name)
                    binding.textViewNameStatus.text = "Nombre: ${if (name.isEmpty()) "No establecido" else name}"
                }
            }
        }

        binding.buttonSaveAge.setOnClickListener {
            val age = binding.editTextAge.text.toString()
            lifecycleScope.launch {
                currentUserEmail?.let { email ->
                    UserPreferencesManager.saveAge(applicationContext, email, age)
                    binding.textViewAgeStatus.text = "Edad: ${if (age.isEmpty()) "No establecida" else age}"
                }
            }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                currentUserEmail?.let { email ->
                    // Guardar en UserPreferencesManager (por usuario)
                    UserPreferencesManager.saveDarkMode(applicationContext, email, isChecked)
                    binding.textViewDarkModeStatus.text = "Modo Oscuro: ${if (isChecked) "Activado" else "Desactivado"}"

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
                    UserPreferencesManager.saveNotifications(applicationContext, email, isChecked)
                    binding.textViewNotificationsStatus.text = "Notificaciones: ${if (isChecked) "Activadas" else "Desactivadas"}"
                }
            }
        }

        binding.buttonVolver.setOnClickListener {
            finish()
        }
    }
}