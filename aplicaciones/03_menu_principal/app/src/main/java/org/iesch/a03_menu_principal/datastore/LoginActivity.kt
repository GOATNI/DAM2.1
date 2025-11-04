package org.iesch.a03_menu_principal.datastore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.MenuActivity
import org.iesch.a03_menu_principal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // FORZAR tema claro (blanco) SIEMPRE en el login
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

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
    }

    override fun onResume() {
        super.onResume()
        // Asegurar que siempre esté en modo claro aunque vuelvas atrás
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun startMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}