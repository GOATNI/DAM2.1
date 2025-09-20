package org.iesch.app00

fun main() {
    var nombre: String? = null
    println(nombre!!.get(8)?: "Es nulo")
}