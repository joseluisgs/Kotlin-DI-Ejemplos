package manual.cafeteras

import java.util.*

data class Cafetera(
    private val calentador: Lazy<Calentador>, // Eso es una propiedad que se inicializa en el momento de su uso
    private val bomba: Bomba
) {
    private val id = UUID.randomUUID()

    fun servir() {
        // El get es porque es Lazy, solo lo creamos cuando lo usemos la 1ª vez, por eso hay que usar el get
        calentador.value.encender()
        bomba.bombear()
        println("[_]P !Taza de Café! [_]P ")
        calentador.value.apagar()
    }
}