package com.example.prepexamen


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PizzaAdapter
    private val apiService = PizzaApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Setup RecyclerView
        recyclerView = findViewById(R.id.pizzaRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PizzaAdapter(emptyList())
        recyclerView.adapter = adapter

        // Setup bottom navigation or buttons
        findViewById<MaterialButton>(R.id.settingsButton).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }



        // Load menu da
        loadMenu()
    }

    // Fetch menu from API using coroutines
    private fun loadMenu() {
        lifecycleScope.launch {
            try {
                // API returns List<Pizza> directly
                val pizzas = apiService.getMenu()

                // Filter only Pizza category items (API returns all items)
                val pizzaItems = pizzas.filter { it.category == "Pizza" }

                adapter.updateData(pizzaItems)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity,
                    "Failed to load menu: ${e.message}",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}