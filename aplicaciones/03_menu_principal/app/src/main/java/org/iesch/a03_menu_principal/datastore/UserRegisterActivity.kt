package org.iesch.a03_menu_principal.datastore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.MenuActivity
import org.iesch.a03_menu_principal.databinding.ActivityRegisterBinding
import org.iesch.a03_menu_principal.databinding.ActivityUserRegisterBinding

class UserRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        // Forzar tema claro
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase
        auth = Firebase.auth
        firestore = Firebase.firestore

        // Botón de registro
        binding.registerButton.setOnClickListener {
            val nombre = binding.nombreEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (validarCampos(nombre, email, password)) {
                registrarUsuario(nombre, email, password)
            }
        }

        // Enlace para volver al login
        binding.yaTenesCuentaTextView.setOnClickListener {
            volverALogin()
        }
    }

    override fun onResume() {
        super.onResume()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }


    // VALIDACIÓN


    private fun validarCampos(nombre: String, email: String, password: String): Boolean {
        return when {
            nombre.isEmpty() -> {
                Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_SHORT).show()
                false
            }
            nombre.length < 3 -> {
                Toast.makeText(this, "El nombre debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show()
                false
            }
            email.isEmpty() -> {
                Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                false
            }
            password.isEmpty() -> {
                Toast.makeText(this, "Ingresa tu contraseña", Toast.LENGTH_SHORT).show()
                false
            }
            password.length < 6 -> {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }


    // REGISTRO


    private fun registrarUsuario(nombre: String, email: String, password: String) {
        // Deshabilitar botón mientras se procesa
        binding.registerButton.isEnabled = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("UserRegisterActivity", "createUserWithEmail:success")
                    val userId = auth.currentUser?.uid

                    if (userId != null) {
                        // Guardar datos del usuario en Firestore
                        guardarUsuarioEnFirestore(userId, nombre, email)
                    } else {
                        binding.registerButton.isEnabled = true
                        mostrarError("Error al obtener ID de usuario")
                    }
                } else {
                    binding.registerButton.isEnabled = true
                    Log.w("UserRegisterActivity", "createUserWithEmail:failure", task.exception)
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthUserCollisionException -> "Este correo ya está registrado"
                        else -> "Error al registrar usuario: ${task.exception?.message}"
                    }
                    mostrarError(errorMessage)
                }
            }
    }

    private fun guardarUsuarioEnFirestore(userId: String, nombre: String, email: String) {
        val userData = hashMapOf(
            "nombre" to nombre,
            "email" to email,
            "fechaRegistro" to com.google.firebase.Timestamp.now()
        )

        firestore.collection("users")
            .document(userId)
            .set(userData)
            .addOnSuccessListener {
                Log.d("UserRegisterActivity", "Usuario guardado en Firestore")

                // Guardar credenciales en DataStore
                lifecycleScope.launch {
                    LoginDataStore.saveCredentials(
                        context = applicationContext,
                        email = email,
                        password = "", // Por seguridad, no guardamos la contraseña en plain text
                        provider = ProviderType.EMAILYCONTRASENA.name
                    )

                    Toast.makeText(
                        this@UserRegisterActivity,
                        "¡Bienvenido, $nombre!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Ir directamente al menú
                    startMenuActivity()
                }
            }
            .addOnFailureListener { e ->
                binding.registerButton.isEnabled = true
                Log.e("UserRegisterActivity", "Error guardando usuario en Firestore", e)
                mostrarError("Usuario creado, pero hubo un error al guardar los datos: ${e.message}")
            }
    }

    // ============================================
    // NAVEGACIÓN
    // ============================================

    private fun volverALogin() {
        finish() // Simplemente cierra esta actividad y vuelve al login
    }

    private fun startMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    // ============================================
    // DIÁLOGOS
    // ============================================

    private fun mostrarError(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }
}