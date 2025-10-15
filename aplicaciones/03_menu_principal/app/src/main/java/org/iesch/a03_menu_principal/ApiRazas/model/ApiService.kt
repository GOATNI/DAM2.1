package org.iesch.a03_menu_principal.ApiRazas.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

// Creamos la interfaz la cual tendra los metodos para obtener los datos de la Api

interface ApiService {

    //aqui usare retrofit y lo primero que he de poner es el tipo de operacion
    //esta funcion resibira por parametro algo una direccion + hound/imagens
    //y devolvera un objeto de tipo dogresponse
    @GET
    suspend fun getperrosraza(@Url url: String): Response<DogsResponse>
    //

}