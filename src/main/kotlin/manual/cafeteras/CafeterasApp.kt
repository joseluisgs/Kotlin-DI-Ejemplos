package manual.cafeteras

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