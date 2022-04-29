import koin.cafeteras.CafeterasApp
import koin.cafeteras.CafeterasModule
import koin.casas.CasasApp
import koin.casas.CasasModule
import koin.personas.PersonasApp
import koin.personas.PersonasModule
import org.koin.core.context.startKoin

fun main() {

    println("Hola Inyección de Dependencias en Kotlin")
    println("=======================================")
    println()
    inyeccionManual()
    inyeccionDagger()
    inyeccionKoin()

}


fun inyeccionManual() {
    println("Inyección de Dependencias Manual")
    println("===============================")
    println()
    manual.casas.main()
    manual.cafeteras.main()
    manual.personas.main()
    manual.myviews.main()
}

fun inyeccionDagger() {
    println("Inyección de Dependencias con Dagger2")
    println("===================================")
    println()
    dagger.casas.main()
    dagger.cafeteras.main()
    dagger.personas.main()
    dagger.myviews.main()
}

fun inyeccionKoin() {
    println("Inyección de Dependencias Koin")
    println("===============================")
    println()
    // Como lo voy a hacer global, cargo el contexto de todos aquí
    startKoin {
        // use Koin logger
        printLogger()
        // declare modules
        modules(CasasModule, CafeterasModule, PersonasModule)
    }
    CasasApp().run()
    CafeterasApp().run()
    PersonasApp().run()
}
