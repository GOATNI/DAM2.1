package org.iesch.a03_menu_principal.ApiRazas.model

import com.google.gson.annotations.SerializedName


data class DogsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>
)

