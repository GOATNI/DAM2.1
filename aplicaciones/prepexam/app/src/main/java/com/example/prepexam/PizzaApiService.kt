package com.example.prepexam

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Interface defining API endpoints
interface PizzaApiService {
    // GET request to fetch menu - returns List<Pizza> directly
    @GET("restaurants/restaurantId/menu?category=Pizza&orderBy=rank")
    suspend fun getMenu(): List<Pizza>

    companion object {
        private const val BASE_URL = "https://private-ca2774-pizzaapp.apiary-mock.com/"

        // Create Retrofit instance
        fun create(): PizzaApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PizzaApiService::class.java)
        }
    }
}