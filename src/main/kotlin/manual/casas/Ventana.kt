package manual.casas

import java.util.*

data class Ventana(val id: UUID = UUID.randomUUID()) {
    private var isOpened: Boolean = false
    fun abrir() {
        println("Abriendo ventana")
        isOpened = true
    }

    fun cerrar() {
        println("Cerrando ventana")
        isOpened = false
    }
}