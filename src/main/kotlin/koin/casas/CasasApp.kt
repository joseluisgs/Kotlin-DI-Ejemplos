package koin.casas

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class CasasApplication : KoinComponent {
    val casa: Casa by inject()

    fun run() {
        println("Ejemplo de Casas Koin")
        println("=======================")
        println("Casa: $casa")
        casa.entrar()
        casa.ventilar()
        println()
    }

}

fun main() {
    // Comenzamos con Koin
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(CasasModule)
    }
    CasasApplication().run()
}