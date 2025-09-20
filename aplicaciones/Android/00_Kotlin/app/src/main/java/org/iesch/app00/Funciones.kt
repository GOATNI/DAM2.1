package org.iesch.app00
var nombre = "help"
var numero1= 23
var numero2 = 34

fun saludar(){
    println("Hola $nombre")
}

fun main() {
    //saludar()
    println(sumar(numero1,numero2))
}

fun sumar(num1: Int, num2: Int): Int {
    var resultado = num1+num2
    return resultado

}