package manual.casas

fun main() {
    println("Ejemplo de Casas Manual")
    println("=======================")
    val casa = Casa(Ventana(), Puerta())
    println("Casa: $casa")
    casa.entrar()
    casa.ventilar()
    println()
}