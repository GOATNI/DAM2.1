package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.ApiRazas.RazasApiActivity
import org.iesch.a03_menu_principal.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRazas.setOnClickListener {
            irARazasActivity()
        }
    }
}

private fun MenuActivity.irARazasActivity() {

    val irarazas = Intent(this, RazasApiActivity::class.java)
    startActivity(irarazas)
}
