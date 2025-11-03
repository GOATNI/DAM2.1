package org.iesch.a03_menu_principal

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.ApiRazas.RazasApiActivity
import org.iesch.a03_menu_principal.Fragments.FragmentActivity
import org.iesch.a03_menu_principal.Quiz.quizmain
import org.iesch.a03_menu_principal.databinding.ActivityMenuBinding
import org.iesch.a03_menu_principal.settings.SettingsActivity
import org.iesch.a03_menu_principal.superhero.RegisterActivity

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema antes de crear la vista
        lifecycleScope.launch {
            SettingsActivity.applyTheme(this@MenuActivity)
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

        // Si no hay sesión, mostramos diálogo de login simple (gestiona LoginDataStore internamente)
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

        // Logout button: clear LoginDataStore, SettingsDataStore and refresh la Activity
        binding.btnLogout.setOnClickListener {
            lifecycleScope.launch {
                // Clear both login credentials and settings
                LoginDataStore.clearCredentials(applicationContext)
                SettingsActivity.clearSettings(applicationContext)
                Toast.makeText(this@MenuActivity, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                // Volver a LoginActivity
                startActivity(Intent(this@MenuActivity, LoginActivity::class.java))
                finish()
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
                        binding.TVBienvenida.text = "Hola $email"
                    }
                }
            }
            .create()

        dialog.show()
    }
}




private fun MenuActivity.irASettings(){
    val iraSettings = Intent(this, SettingsActivity::class.java)
    startActivity(iraSettings)
}



private fun MenuActivity.irAMenuFragments() {
    val irAFragments = Intent(this, FragmentActivity::class.java)
    startActivity(irAFragments)
}
private fun MenuActivity.irARazasActivity() {

    val irarazas = Intent(this, RazasApiActivity::class.java)
    startActivity(irarazas)
}
private fun MenuActivity.iraquiz(){
    val iraquizq= Intent(this, quizmain::class.java)
    startActivity(iraquizq)
}
private fun MenuActivity.calcularor(){
    val iracalculadora = Intent(this, Calculadora::class.java)
    startActivity(iracalculadora)
}
private fun MenuActivity.superhero(){
    val iracalculadora = Intent(this, RegisterActivity::class.java)
    startActivity(iracalculadora)
}
