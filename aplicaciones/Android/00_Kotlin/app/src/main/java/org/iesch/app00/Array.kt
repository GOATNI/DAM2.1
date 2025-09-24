package org.iesch.app00

var weekDays = arrayOf("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo")

fun main() {
    println(weekDays)
    println(weekDays[2])
    println(weekDays.size)

    /*for((position,value )in weekDays.withIndex()){
        println("la posicion $position contiene $value")
    }*/

    /*for (valor in weekDays){
        println("El dia es $valor")
    }*/


    weekDays.forEach { dia -> println(dia) }

}