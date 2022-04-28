package manual.casas

import java.util.*

data class Puerta(private val id: UUID = UUID.randomUUID()) {
    private var isOpened: Boolean = false
    fun abrir() {
        println("Abriendo puerta")
        isOpened = true
    }

    fun cerrar() {
        println("Cerrando puerta")
        isOpened = false
    }

    override fun toString(): String {
        return "dagger.casas.Puerta(id='$id', isOpened=$isOpened)"
    }
}