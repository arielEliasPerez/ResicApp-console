package interfaz

import data.Options

object Interfaz {
    fun showHomeMain() {
        //Mostrar menu principal por consola
    }

    fun validateOption(lower: Int, upper: Int): Options {
        var option: Int
        do{
            option = readln().toInt()
        }while (option !in lower..upper)

        return Options.values().find{it.num == option}!!
    }

}