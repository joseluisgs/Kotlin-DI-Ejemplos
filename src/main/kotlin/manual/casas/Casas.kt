package manual.casas

fun main() {
    println("Ejemplo de Casas Manual")
    val casa = Casa(Ventana(), Puerta())
    casa.entrar()
    casa.ventilar()
    println()
}