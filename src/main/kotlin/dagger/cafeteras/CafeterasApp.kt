package dagger.cafeteras

fun main() {
    println("Ejemplo de Cafeteras Dagger")
    println("===========================")
    val cafetera = DaggerCafeterasDI.create().build()
    println(cafetera)
    cafetera.servir()
    println()
}