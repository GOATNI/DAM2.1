package org.iesch.a02_registro_superheroes.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuperHeroe {
    val nombre:String,
    val ego : String,
    val bio: String,
    val power: Float

}