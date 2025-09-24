package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.databinding.ActivityregisterBinding
import org.iesch.a02_registro_superheroes.detalle.detalleHeroeActivity

class registerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityregisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityregisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activityregister)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnGuardar.setOnClickListener {
            val superheroname = binding.etheroname.text.toString()
            val alterego = binding.etAlterEgo.text.toString()
            val bio = binding.etbio.text.toString()
            val power = binding.rtPower.rating

            iraldetalle(superheroname,alterego,bio,power)

        }
    }
}

private fun registerActivity.iraldetalle(superheroname:String,alterego:String,bio:String,power: Float) {
    //Al pulsar el boton quirto ir a la otra pagina
    //el intent debe tener claro desde donde les llama y donde va
    val intent = Intent(this, detalleHeroeActivity::class.java)

    intent.putExtra("heroName",superheroname)
    intent.putExtra("alter_ego",alterego)
    intent.putExtra("bio",bio)
    intent.putExtra("power",power)

    startActivity(intent)
}
