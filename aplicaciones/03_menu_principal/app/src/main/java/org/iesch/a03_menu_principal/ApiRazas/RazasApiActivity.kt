package org.iesch.a03_menu_principal.ApiRazas

import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.ApiRazas.model.ApiService
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivityRazasApiBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RazasApiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRazasApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRazasApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //paso 5 creamos instancia de retrofit

    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    //hasta aqui lo de retrofit
    //6 implementar la funcion de buscar por raza
    private fun BuscarPorRaza(raza: String){
        CoroutineScope(Dispatchers.IO).launch{
            // tódo lo que se ejecute qui se esta ejecutando en un hilo secundario
            val call = getRetrofit().create<ApiService>(ApiService::class.java).getperrosraza("$raza/images")
            val perros = call.body()
            if (call.isSuccessful){

            }
        }
    }

}