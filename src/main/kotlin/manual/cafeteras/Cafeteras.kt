package manual.cafeteras

fun main() {
    println("Ejemplo de Cafeteras Manual")
    val cafetera: Cafetera = Cafetera(lazy { CalentadorElectrico() }, Termosifon(CalentadorElectrico()))
    println(cafetera)
    cafetera.servir()
    println(cafetera)
    println()
}