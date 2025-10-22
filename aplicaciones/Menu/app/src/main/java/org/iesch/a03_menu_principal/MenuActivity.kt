package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.apirazas.RazasApiActivity
import org.iesch.a03_menu_principal.databinding.ActivityMenuBinding
import org.iesch.a03_menu_principal.edadcanina.EdadCaninaActivity
import org.iesch.a03_menu_principal.fragments.FragmentsActivity
import org.iesch.a03_menu_principal.superheroes.RegistroSuperHeroeActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate( layoutInflater )
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnRazas.setOnClickListener {
            irARazasActivity()
        }
        binding.btnFragments.setOnClickListener {
            irAMenuFragments()
        }
        binding.btnEdadCanina.setOnClickListener {
            irAEdadCanina()
        }
        binding.btnSuperheroes.setOnClickListener {
            irASuperHeroes()
        }

    }

    private fun irAMenuFragments() {
        val irAFragments = Intent(this, FragmentsActivity::class.java)
        startActivity(irAFragments)
    }
    private fun irAEdadCanina() {
        val irAEdadCanina = Intent(this, EdadCaninaActivity::class.java)
        startActivity(irAEdadCanina)
    }
    private fun irASuperHeroes() {
        val irASuperHeroes = Intent(this, RegistroSuperHeroeActivity::class.java)
        startActivity(irASuperHeroes)
    }
}

private fun MenuActivity.irARazasActivity() {
    val irARazas = Intent(this, RazasApiActivity::class.java)
    startActivity(irARazas)
}
