package consoleUI

object LoginUI {
    fun showWelcome() {
        println(
            """
            
            ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
                          ¡¡¡ BIENVENIDO !!!
            ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓
            
        """.trimIndent()
        )
    }

    fun requestUserName(): String {
        print("► Ingrese su NickName: ")
        return readln()
    }

    fun requestPassword(): String {
        print("\n► Ingrese la contraseña: ")
        return readln()
    }

    fun incorrectUser() {
        println("\n\nEl NickName o la contraseña ingresada es INCORRECTA!!\n")
    }

    fun confirmRetry(): Boolean {
        println("Desea intentarlo otra vez?")
        print(
            """
            s --> Si
            n --> No
            
            --> 
        """.trimIndent()
        )
        var answer = readln().first()

        while (!answer.equals('s', true) && !answer.equals('n', true)) {
            print("¡Respuesta erronea! Intente otra vez --> ")
            answer = readln()[0]
        }
        println("")
        return answer.equals('s', true)
    }
}