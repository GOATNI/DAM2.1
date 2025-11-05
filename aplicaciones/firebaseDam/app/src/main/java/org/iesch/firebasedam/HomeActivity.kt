package org.iesch.firebasedam

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.iesch.firebasedam.databinding.ActivityHomeBinding


enum class ProviderType{
    EMAILYCONTRASENA,
    GOOGLE
}
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate( layoutInflater )
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recuperamos los valores del Bundle
        val bundle = intent.extras
        val email = bundle?.getString("usuario")
        val provider = bundle?.getString("provider")

        // Lo mostramos en los textview para ello
        binding.emailTextView.text = email.toString()
        binding.metodoTextView.text = provider.toString()

        // Listener para el boton de cerrar sesion
        binding.logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }
    }
}