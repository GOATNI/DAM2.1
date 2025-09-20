package org.iesch.app00

fun main() {
    listasMutables()
}

fun listasMutables(){
    val weekDays = mutableListOf<String>("Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo")
    println(weekDays)

    /*if (weekDays.isEmpty()){
        println("la lista esta vacia")
    }else{
        weekDays.forEach { println(it) }
    }

    if(weekDays.isNotEmpty()){
        println("la lista no esta vacia")
    }*/
    println(weekDays.last() )
    println(weekDays.first())
    //filtrar en listas
    println(weekDays.filter { it.contains('a') })
    weekDays.add(2,"chomonero")
    println(weekDays)

}