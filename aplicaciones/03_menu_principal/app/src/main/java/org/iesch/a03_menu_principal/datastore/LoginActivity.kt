package org.iesch.a03_menu_principal.datastore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.lifecycleScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.MenuActivity
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivityLoginBinding
import org.iesch.a03_menu_principal.notifications.FCMTokenManager


enum class ProviderType {
    EMAILYCONTRASENA,
    GOOGLE
}

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase Auth y Firestore
        auth = Firebase.auth
        firestore = Firebase.firestore

        // Comprobar si ya hay sesión activa
        verificarSesionActiva()

        // Botón de Login
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (validarCampos(email, password)) {
                loginConEmailPassword(email, password)
            }
        }

        // Login con Google
        binding.loginGoogleButton.setOnClickListener {
            logueoConGoogle()
        }

        // Ir a pantalla de registro (ahora es TextView)
        binding.registerButton.setOnClickListener {
            val intent = Intent(this, UserRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        verificarSesionActiva()
    }

    override fun onResume() {
        super.onResume()
    }


    // VALIDACIÓN


    private fun validarCampos(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
                false
            }
            password.isEmpty() -> {
                Toast.makeText(this, "Ingresa tu contraseña", Toast.LENGTH_SHORT).show()
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                false
            }
            password.length < 6 -> {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }


    // SESIÓN ACTIVA


    private fun verificarSesionActiva() {
        lifecycleScope.launch {
            val isLoggedDataStore = LoginDataStore.isLoggedFlow(applicationContext).first()
            val currentUser = auth.currentUser

            if (isLoggedDataStore && currentUser != null) {
                startMenuActivity()
            }
        }
    }


    // LOGIN CON EMAIL Y PASSWORD


    private fun loginConEmailPassword(email: String, password: String) {
        binding.loginButton.isEnabled = false

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                binding.loginButton.isEnabled = true

                if (task.isSuccessful) {
                    Log.d("LoginActivity", "signInWithEmail:success")
                    lifecycleScope.launch {
                        LoginDataStore.saveCredentials(
                            context = applicationContext,
                            email = email,
                            password = "", // Por seguridad
                            provider = ProviderType.EMAILYCONTRASENA.name
                        )
                        startMenuActivity()
                    }
                } else {
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "Usuario no encontrado"
                        is FirebaseAuthInvalidCredentialsException -> "Contraseña incorrecta"
                        else -> "Error al iniciar sesión. Verifica tus datos."
                    }
                    mostrarError(errorMessage)
                }
            }
    }


    // LOGIN CON GOOGLE


    private fun logueoConGoogle() {
        Log.d("LoginActivity", "Iniciando login con Google...")

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.web_client))
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                Log.d("LoginActivity", "Solicitando credenciales de Google...")
                val credentialManager = CredentialManager.create(this@LoginActivity)
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@LoginActivity
                )
                Log.d("LoginActivity", "Credenciales obtenidas exitosamente")
                handleSignIn(result.credential)
            } catch (e: NoCredentialException) {
                Log.e("LoginActivity", "NoCredentialException: ${e.message}")
                mostrarDialogoNoGoogleAccount()
            } catch (e: GetCredentialCancellationException) {
                Log.w("LoginActivity", "Usuario canceló el login con Google")
                Toast.makeText(this@LoginActivity, "Login cancelado", Toast.LENGTH_SHORT).show()
            } catch (e: GetCredentialException) {
                Log.e("LoginActivity", "GetCredentialException: ${e.message}", e)
                mostrarError("Error al obtener credenciales de Google.\n\nAsegúrate de tener una cuenta de Google configurada en tu dispositivo.")
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error inesperado: ${e.message}", e)
                mostrarError("Error inesperado al iniciar sesión con Google")
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        when {
            credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
                try {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    Log.d("LoginActivity", "Token de Google obtenido, autenticando con Firebase...")
                    firebaseAuthWithGoogle(googleIdTokenCredential.idToken)
                } catch (e: Exception) {
                    Log.e("LoginActivity", "Error al procesar token de Google: ${e.message}", e)
                    mostrarError("Error al procesar las credenciales de Google")
                }
            }
            else -> {
                Log.w("LoginActivity", "Tipo de credencial no compatible: ${credential.type}")
                mostrarError("Tipo de credencial no compatible")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginActivity", "signInWithCredential:success")
                    val user = auth.currentUser
                    val email = user?.email ?: ""
                    val userId = user?.uid ?: ""

                    // Verificar si el usuario ya existe en Firestore
                    firestore.collection("users").document(userId).get()
                        .addOnSuccessListener { document ->
                            if (!document.exists()) {
                                // Usuario nuevo de Google, crear entrada en Firestore
                                val userData = hashMapOf(
                                    "nombre" to (user?.displayName ?: "Usuario Google"),
                                    "email" to email,
                                    "fechaRegistro" to com.google.firebase.Timestamp.now()
                                )
                                firestore.collection("users").document(userId).set(userData)
                            }

                            lifecycleScope.launch {
                                LoginDataStore.saveCredentials(
                                    context = applicationContext,
                                    email = email,
                                    password = "",
                                    provider = ProviderType.GOOGLE.name
                                )
                                Toast.makeText(
                                    this@LoginActivity,
                                    "¡Bienvenido, $email!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startMenuActivity()
                            }
                        }
                        .addOnFailureListener { e ->
                            Log.e("LoginActivity", "Error verificando usuario en Firestore", e)
                            // Continuar con el login aunque falle Firestore
                            lifecycleScope.launch {
                                LoginDataStore.saveCredentials(
                                    context = applicationContext,
                                    email = email,
                                    password = "",
                                    provider = ProviderType.GOOGLE.name
                                )
                                startMenuActivity()
                            }
                        }
                } else {
                    Log.e("LoginActivity", "Error al autenticar con Firebase", task.exception)
                    val errorMessage = "Error al iniciar sesión con Google.\n\n${task.exception?.message}"
                    mostrarError(errorMessage)
                }
            }
    }


    // DIÁLOGO ESPECIAL PARA NO GOOGLE ACCOUNT


    private fun mostrarDialogoNoGoogleAccount() {
        AlertDialog.Builder(this)
            .setTitle("Sin cuenta de Google")
            .setMessage(
                "No hay ninguna cuenta de Google configurada en este dispositivo.\n\n" +
                        "Para usar el login con Google:\n" +
                        "1. Ve a Ajustes del dispositivo\n" +
                        "2. Busca 'Cuentas' o 'Usuarios y cuentas'\n" +
                        "3. Añade una cuenta de Google\n" +
                        "4. Vuelve a intentar el login\n\n" +
                        "O puedes usar el login con email y contraseña."
            )
            .setPositiveButton("Entendido", null)
            .setNeutralButton("Usar Email/Password") { _, _ ->
                binding.emailEditText.requestFocus()
            }
            .create()
            .show()
    }


    // NAVEGACIÓN


    private fun startMenuActivity() {
        val intent = Intent(this, MenuActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    // DIÁLOGOS


    private fun mostrarError(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .create()
            .show()
    }
}