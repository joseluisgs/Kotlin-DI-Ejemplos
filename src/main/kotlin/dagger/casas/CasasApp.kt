package dagger.casas

fun main() {
    println("Ejemplo de Casas Dagger")
    println("=======================")
    val casa = DaggerCasasDI.create().build()
    println(casa)
    casa.entrar()
    casa.ventilar()
    println()
}