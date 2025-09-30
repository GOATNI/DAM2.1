package org.iesch.a02_registro_superheroes.detalle

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.R
import org.iesch.a02_registro_superheroes.databinding.ActivityDetalleHeroeBinding
import org.iesch.a02_registro_superheroes.databinding.ActivityregisterBinding

class detalleHeroeActivity : AppCompatActivity() {
    companion object{
        const val HERO_NAME ="heroName"

        const val Alter_ego ="alter_ego"

        const val bio = "bio"

        const val Power = "power"
    }
    private lateinit var binding: ActivityDetalleHeroeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalleHeroeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detalle_heroe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recibir los datos
        //un objeto bandel en un paquete dentro del cual se transmiten muchas cosas

        val bundel = intent.extras!!
        val superheroname = bundel.getString(HERO_NAME) ?: "no hay nombre"
        val alterego = bundel.getString(Alter_ego) ?: "no hay alter ego"
        val bio = bundel.getString(bio) ?: "no hay bio"
        val power = bundel.getFloat(Power)
        //rellenamos con los campos que hemos recibidos del intent
        binding.tvHeroNameResult.text = superheroname
        binding.tvAlteregoresult.text = alterego
        binding.tvBioResult.text = bio
        binding.rbresultado.rating = power
    }
}