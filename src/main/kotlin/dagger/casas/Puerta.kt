package dagger.casas

import java.util.*
import javax.inject.Inject

class Puerta
@Inject constructor() {
    private val id: UUID = UUID.randomUUID()
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
        return "Puerta(id='$id', isOpened=$isOpened)"
    }
}