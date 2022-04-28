fun main() {
    println("Hola Inyección de Dependencias en Kotlin")
    println("=======================================")
    println()
    inyeccionManual()
    inyeccionDagger()

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
    println("Inyección Dagger")
    println("================")
    println()
    dagger.casas.main()
}
