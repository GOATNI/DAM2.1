package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.databinding.ActivityLoginBinding
import org.iesch.a03_menu_principal.settings.SettingsActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema antes de crear la vista
        lifecycleScope.launch {
            SettingsActivity.applyTheme(this@LoginActivity)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Comprobar si ya hay sesión
        lifecycleScope.launch {
            val isLogged = LoginDataStore.isLoggedFlow(applicationContext).first()
            if (isLogged) {
                startMenuActivity()
                return@launch
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar credenciales y marcar como logueado
            lifecycleScope.launch {
                LoginDataStore.saveCredentials(applicationContext, email, password)
                startMenuActivity()
            }
        }

        binding.btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Volver a aplicar el tema cuando se regrese de Settings
        lifecycleScope.launch {
            SettingsActivity.applyTheme(this@LoginActivity)
        }
    }

    private fun startMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}
