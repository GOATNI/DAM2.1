package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import org.iesch.a03_menu_principal.ApiRazas.RazasApiActivity
import org.iesch.a03_menu_principal.Fragments.FragmentActivity
import org.iesch.a03_menu_principal.Quiz.quizmain
import org.iesch.a03_menu_principal.databinding.ActivityMenuBinding
import org.iesch.a03_menu_principal.superhero.RegisterActivity

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
        val nombre = intent.getStringExtra("nombre")
        binding.TVBienvenida.text = "Hola $nombre"


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
    }
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


