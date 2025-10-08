package org.iesch.a4_recyclerbasicoandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.iesch.a4_recyclerbasicoandroid.adapter.VersionesAndroidAdapter

class MainActivity : AppCompatActivity() {

    //paso 2: Me declaro la informacion que voy a mostrar en mi recycler
    val listaVersionesAndroid = mutableListOf(
        "Android 15",
        "Android 14 (Upside Down Cake)",
        "Android 13 (Tiramisu)",
        "Android 12L",
        "Android 12 (Snow Cone)",
        "Android 11 (Red Velvet Cake)",
        "Android 10 (Quince Tart)",
        "Pie",
        "Oreo",
        "Nougat",
        "Marshmallow",
        "Lollipop",
        "KitKat",
        "Jelly Bean",
        "Ice Cream Sandwich",
        "Honeycomb",
        "Gingerbread",
        "Froyo",
        "Eclair",
        "Donut",
        "Cupcake"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // creo esta funcion para cargar el recycler
        initRecyclerview()
    }

    private fun initRecyclerview() {
        //Configuramos el Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.rv_versiones_android)
        // le asignamos el layoutManager
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val Dividir = DividerItemDecoration(this,layoutManager.orientation)
        recyclerView.adapter = VersionesAndroidAdapter(listaVersionesAndroid)

    }
}