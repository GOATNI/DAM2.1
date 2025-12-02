package com.example.prepexamen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter to bind pizza data to RecyclerView
class PizzaAdapter(private var pizzas: List<Pizza>) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    // ViewHolder holds references to item views
    class PizzaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.pizzaName)
        val descText: TextView = view.findViewById(R.id.pizzaDescription)
        val priceText: TextView = view.findViewById(R.id.pizzaPrice)
    }

    // Create new ViewHolder when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pizza, parent, false)
        return PizzaViewHolder(view)
    }

    // Bind data to ViewHolder at specific position
    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val pizza = pizzas[position]
        holder.nameText.text = pizza.name

        // Display toppings as comma-separated list or category
        holder.descText.text = if (pizza.topping != null && pizza.topping.isNotEmpty()) {
            pizza.topping.joinToString(", ")
        } else {
            pizza.category
        }

        holder.priceText.text = "${pizza.price} kr"
    }

    // Return total number of items
    override fun getItemCount() = pizzas.size

    // Update adapter data and refresh view
    fun updateData(newPizzas: List<Pizza>) {
        pizzas = newPizzas
        notifyDataSetChanged()
    }
}
