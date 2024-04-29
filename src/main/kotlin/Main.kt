import data.Options
import interfaz.Interfaz
import repositories.UserRepository

fun main(args: Array<String>) {
    UserRepository.login()

    do{
        Interfaz.showHomeMain()
        val option: Options = Interfaz.validateOption(lower = 0, upper = 3)
        clasifyOption(option)
    }while(option != Options.SALIR);

}

fun clasifyOption(option: Options){
    when(option){
        Options.LIBRO -> {
            //Filtrar y mostrar Libros
        }
        Options.MUSICA -> {
            //Filtrar y mostrar musica
        }
        Options.HISTORIAL -> {
            //mostrar historial
        }
        Options.SALIR -> {
            return
        }
    }
}
