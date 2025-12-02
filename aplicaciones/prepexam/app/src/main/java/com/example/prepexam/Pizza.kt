package com.example.prepexam 

data class Pizza(
    val id: Int,
    val category: String,
    val name: String,
    val topping: List<String>? = null, // List of toppings (nullable)
    val price: Int,
    val rank: Int? = null // Rank for sorting (nullable)
)