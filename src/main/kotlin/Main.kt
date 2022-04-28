fun main() {
    println("Hola Inyección de Dependencias en Kotlin")
    println("=======================================")
    println()
    inyeccionManual()
    inyeccionDagger()
    inyeccionKoin()

}


fun inyeccionManual() {
    println("Inyección Manual")
    println("================")
    println()
    manual.casas.main()
    manual.cafeteras.main()
    manual.personas.main()
    manual.myviews.main()
}

fun inyeccionDagger() {
    println("Inyección Dagger2")
    println("================")
    println()
    dagger.casas.main()
    dagger.cafeteras.main()
    dagger.personas.main()
    dagger.myviews.main()
}

fun inyeccionKoin() {
    println("Inyección Koin")
    println("==============")
    println()
    koin.casas.main()

}
