import data.Options

fun main(args: Array<String>) {
    ResicSystem.initLogin()

    ResicSystem.filterProducts()

    do{
        val option: Options = ResicSystem.homeMain()

        ResicSystem.processOption(option)

    }while(option != Options.SALIR)
}
