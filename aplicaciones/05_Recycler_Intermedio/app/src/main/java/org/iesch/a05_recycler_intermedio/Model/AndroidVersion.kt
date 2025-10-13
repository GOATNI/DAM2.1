package org.iesch.a05_recycler_intermedio.model

// 1 - Creamos el modelo de datos.
// Uso data class
data class AndroidVersion (
    val nombre: String,
    val codigo: String,
    val apiLevel: Int,
    val anioLanzamiento: Int
)