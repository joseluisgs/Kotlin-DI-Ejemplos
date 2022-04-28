package manual.casas

import koin.casas.Casa
import koin.casas.Puerta
import koin.casas.Ventana

fun main() {
    println("Ejemplo de Casas Manual")
    println("=======================")
    val casa = Casa(Ventana(), Puerta())
    println("Casa: $casa")
    casa.entrar()
    casa.ventilar()
    println()
}