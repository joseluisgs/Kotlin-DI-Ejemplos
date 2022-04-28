package manual.cafeteras

import dagger.cafeteras.Bomba
import dagger.cafeteras.Cafetera
import dagger.cafeteras.Calentador
import dagger.cafeteras.CalentadorElectrico
import dagger.cafeteras.Termosifon

fun main() {
    println("Ejemplo de Cafeteras Manual")
    println("===========================")
    val calentador: Calentador = CalentadorElectrico()
    val bomba: Bomba = Termosifon(calentador)
    val cafetera = Cafetera(lazy { calentador }, bomba)
    println(cafetera)
    cafetera.servir()
    println(cafetera)
    println()
}