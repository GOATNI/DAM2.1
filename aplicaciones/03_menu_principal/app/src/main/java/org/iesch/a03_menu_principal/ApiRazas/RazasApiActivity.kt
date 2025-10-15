package org.iesch.a03_menu_principal.ApiRazas

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.iesch.a03_menu_principal.ApiRazas.adaptador.DogAdapter
import org.iesch.a03_menu_principal.ApiRazas.model.ApiService
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivityRazasApiBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query


class RazasApiActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener{
    private lateinit var adapter: DogAdapter
    private lateinit var binding: ActivityRazasApiBinding
    private val dogimages = mutableListOf<String>()
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

        binding.svDogs.setOnQueryTextListener(this)
         initRecyclerview()

    }

    private fun initRecyclerview() {
        //iniciamos el recycler view
        adapter = DogAdapter(dogimages)
        binding.rvDogs.layoutManager = LinearLayoutManager(this)
        binding.rvDogs.adapter = adapter
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
            val puppies = call.body()
            //Estoy en un Hilo Secundario y para pintar respuesta nesecito volver a hilo principal lo hare mediante run on uithread
            runOnUiThread {
                //como el if pintara untoat o el recycler lo metemos en el hilo principal
                if ( call.isSuccessful ){
                    //to codigo ejecutado aqui lo hara en el hilo principal
                    //almacenamos en una variable las imagenes
                    val imagenes = puppies?.images ?:emptyList()
                    //borro tódo lo que tengamos
                    dogimages.clear()
                    dogimages.addAll(imagenes)
                    //avisamos a el adaptador que han habido cambios
                    adapter.notifyDataSetChanged()
                    // Mostraremos el RecyclerView
                } else {
                    // Mostraremos un error en un Toast
                    showerror()

                }


            }

        }

    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            BuscarPorRaza(query.lowercase())
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true

    }
    private fun showerror() {
        Toast.makeText(this,"ha occurido un error",Toast.LENGTH_LONG).show()
    }


}

