package org.iesch.app00




fun main() {
    getMonth(5);
    getSemester(5)
    getTrimester(8)
    resultado(true)
}

fun getMonth(mes: Int){
    when (mes){
        1 -> print("Enero")
        2 -> print("febrero")
        3 -> print("Marzo")
        4 -> print("abril")
        5 -> print("mayo")
        6 -> print("junio")
        7 -> print("julio")
        8 -> print("Agosto")
        9 -> print("septiembre")
        10 -> print("octubre")
        11 -> print("noviembre")
        12 -> print("diciembre")
        else -> println("Mes erroneo")
    }
}
fun getTrimester(mes: Int){
    when (mes){
        1,2,3 -> println("Primer Trimestre")
        4,5,6 -> print("Segundo Trimestre")
        7,8,9 -> println("Tercer Trimestre")
        10,11,12 -> println("Cuarto trimestre")
        else -> println("Mes erroneo")
    }

}


fun getSemester(mes: Int){
    when (mes){
        in 1..6 -> println("Primer Simestre")
        in 7..12 -> print("Segundo Simestre")
        !in 1..12 -> println("Mes erroneo")
    }

}


fun resultado(valor: Any){
    when (valor){
        is Int -> valor+valor
        is String -> println(valor)
        is Boolean -> if(valor) print("Es booleano y vale true")
    }
}








