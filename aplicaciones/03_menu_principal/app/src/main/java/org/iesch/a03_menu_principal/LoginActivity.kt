package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.iesch.a03_menu_principal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ejemplo: usuario y contraseña fijos (puedes cambiarlos)
        val usuarioValido = "Azeem"
        val passwordValida = "1234"

        binding.btnLogin.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (nombre == usuarioValido && password == passwordValida) {
                // Login correcto
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("nombre", nombre)
                startActivity(intent)
                finish()
            } else {
                // Login incorrecto
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
