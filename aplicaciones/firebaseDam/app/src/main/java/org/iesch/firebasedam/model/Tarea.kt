package org.iesch.firebasedam.model

data class Tarea(
    var id: String = "",
    var titulo: String = "",
    var descripcion: String = "",
    var completada: Boolean = false
) {
    // 5 - Me creo una funcion para convertir esos datos en un Map
    fun toMap(): Map<Any, Any> {
        return mapOf(
            "id" to id,
            "titulo" to titulo,
            "descripcion" to descripcion,
            "completada" to completada
        )
    }
}