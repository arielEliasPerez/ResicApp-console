import data.Options

fun main(args: Array<String>) {
    try{
        ResicSystem.initLogin()
    }catch (e: Exception){
        println(e.message)
        return
    }

    ResicSystem.filterProducts()

    do{
        val option: Options = ResicSystem.homeMain()

        ResicSystem.processOption(option)

    }while(option != Options.SALIR)
}
