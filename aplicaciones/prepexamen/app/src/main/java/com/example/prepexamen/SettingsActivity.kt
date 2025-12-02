package com.example.prepexamen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {

    private lateinit var prefsRepository: PreferencesRepository

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize preferences repository
        prefsRepository = PreferencesRepository(this)

        // Get switch references
        val darkModeSwitch = findViewById<SwitchMaterial>(R.id.darkModeSwitch)
        val notificationSwitch = findViewById<SwitchMaterial>(R.id.notificationSwitch)

        // Observe dark mode preference
        lifecycleScope.launch {
            prefsRepository.darkModeFlow.collect { isDarkMode ->
                darkModeSwitch.isChecked = isDarkMode
            }
        }

        // Observe notification preference
        lifecycleScope.launch {
            prefsRepository.notificationFlow.collect { isEnabled ->
                notificationSwitch.isChecked = isEnabled
            }
        }

        // Dark mode switch listener
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                prefsRepository.setDarkMode(isChecked)
                // You can apply theme change here
            }
        }

        // Notification switch listener
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                prefsRepository.setNotifications(isChecked)
            }
        }
    }
}