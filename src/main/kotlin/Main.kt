import data.Options


fun main(args: Array<String>) {
    ResicSystem.initLogin()

    ResicSystem.filterProducts()

    do{
        val option: Options = ResicSystem.homeMain()

        ResicSystem.processOption(option)

    }while(option != Options.SALIR)

}



/*
    Para optimizar lo señalado en los comentarios previos,
    se podría considerar usar una clase ResicSystem (o el nombre a discutir)
    donde liberará el trabajo del Main,
    tendrá propiedades donde se alojen las listas filtradas al comienzo del programa,
    tendrá una propiedad user para que se guarde el usuario logueado y poder usarlo en cualquier metodo
 */