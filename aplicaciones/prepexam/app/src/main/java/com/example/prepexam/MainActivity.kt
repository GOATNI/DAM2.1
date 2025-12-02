package com.example.prepexam

import kotlin.text.category


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PizzaAdapter
    private val apiService = PizzaApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth


        // Setup RecyclerView
        recyclerView = findViewById(R.id.pizzaRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PizzaAdapter(emptyList())
        recyclerView.adapter = adapter



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