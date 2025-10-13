package org.iesch.a05_recycler_intermedio

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.iesch.a05_recycler_intermedio.adapter.AndroidVersionAdapter
import org.iesch.a05_recycler_intermedio.databinding.ActivityMainBinding
import org.iesch.a05_recycler_intermedio.provider.AndroidVersionProvider

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: AndroidVersionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()

    }

    private fun initRecyclerView() {
        // Obtenemos la lista de versiones android
        val versionesAndroid = AndroidVersionProvider.androidVersionsList

        // configuro el adaptador
        adapter = AndroidVersionAdapter(versionesAndroid)

        // Configurar RecyclerView usando binding
        binding.rvVersionesAndroid.layoutManager = LinearLayoutManager(this)
        binding.rvVersionesAndroid.adapter = adapter
    }
}