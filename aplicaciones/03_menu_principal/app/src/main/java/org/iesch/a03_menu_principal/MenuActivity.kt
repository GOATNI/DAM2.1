package org.iesch.a03_menu_principal

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.ApiRazas.RazasApiActivity
import org.iesch.a03_menu_principal.Fragments.FragmentActivity
import org.iesch.a03_menu_principal.Quiz.quizmain
import org.iesch.a03_menu_principal.databinding.ActivityMenuBinding
import org.iesch.a03_menu_principal.datastore.LoginActivity
import org.iesch.a03_menu_principal.datastore.LoginDataStore
import org.iesch.a03_menu_principal.preferences.UserPreferencesActivity
import org.iesch.a03_menu_principal.preferences.UserPreferencesManager
import org.iesch.a03_menu_principal.settings.SettingsActivity
import org.iesch.a03_menu_principal.superhero.RegisterActivity

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
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
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Si no hay sesión, mostramos diálogo de login simple
        lifecycleScope.launch {
            val logged = LoginDataStore.isLoggedFlow(applicationContext).first()
            if (!logged) {
                showLoginDialog()
                return@launch
            }
            // si está logueado, mostramos el email/nombre guardado
            val email = LoginDataStore.getEmailFlow(applicationContext).first() ?: "usuario"
            binding.TVBienvenida.text = "Hola $email"
        }

        binding.btnRazas.setOnClickListener {
            irARazasActivity()
        }
        binding.btnquizz.setOnClickListener {
            iraquiz()
        }
        binding.CalculatorBack.setOnClickListener {
            calcularor()
        }
        binding.btnregister.setOnClickListener {
            superhero()
        }
        binding.btnFragments.setOnClickListener {
            irAMenuFragments()
        }
        binding.btnsettings.setOnClickListener {
            irASettings()
        }
        binding.btnUserPreferences.setOnClickListener {
            irAUserPreferences()
        }

        // Logout button: solo limpiar credenciales de login, mantener preferencias
        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                // Solo limpiar credenciales de login
                LoginDataStore.clearCredentials(applicationContext)
                Toast.makeText(this@MenuActivity, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                // Volver a LoginActivity
                startActivity(Intent(this@MenuActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Recargar el tema cuando volvemos de Settings o UserPreferences
        lifecycleScope.launch {
            currentUserEmail?.let { email ->
                val isDarkMode = UserPreferencesManager.getDarkModeFlow(applicationContext, email).first()
                val mode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                AppCompatDelegate.setDefaultNightMode(mode)
            }
        }
    }

    private fun showLoginDialog() {
        val dlgView = LayoutInflater.from(this).inflate(R.layout.dialog_login, null)
        val etEmail = dlgView.findViewById<EditText>(R.id.dlg_etEmail)
        val etPassword = dlgView.findViewById<EditText>(R.id.dlg_etPassword)

        val dialog = AlertDialog.Builder(this)
            .setView(dlgView)
            .setCancelable(false)
            .setPositiveButton("Entrar") { d, _ ->
                // validar y guardar
                val email = etEmail.text.toString().trim()
                val pass = etPassword.text.toString().trim()
                if (email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                    // volver a mostrar dialogo
                    d.dismiss()
                    showLoginDialog()
                } else {
                    lifecycleScope.launch {
                        LoginDataStore.saveCredentials(applicationContext, email, pass)
                        currentUserEmail = email
                        binding.TVBienvenida.text = "Hola $email"
                    }
                }
            }
            .create()

        dialog.show()
    }

    private fun irASettings() {
        val iraSettings = Intent(this, SettingsActivity::class.java)
        startActivity(iraSettings)
    }

    private fun irAMenuFragments() {
        val irAFragments = Intent(this, FragmentActivity::class.java)
        startActivity(irAFragments)
    }

    private fun irARazasActivity() {
        val irarazas = Intent(this, RazasApiActivity::class.java)
        startActivity(irarazas)
    }

    private fun iraquiz() {
        val iraquizq = Intent(this, quizmain::class.java)
        startActivity(iraquizq)
    }

    private fun calcularor() {
        val iracalculadora = Intent(this, Calculadora::class.java)
        startActivity(iracalculadora)
    }

    private fun superhero() {
        val iracalculadora = Intent(this, RegisterActivity::class.java)
        startActivity(iracalculadora)
    }

    private fun irAUserPreferences() {
        startActivity(Intent(this, UserPreferencesActivity::class.java))
    }
}