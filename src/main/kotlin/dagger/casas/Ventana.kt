package dagger.casas

import java.util.*
import javax.inject.Inject

class Ventana
@Inject constructor() {
    private val id: UUID = UUID.randomUUID()
    private var isOpened: Boolean = false
    fun abrir() {
        println("Abriendo ventana")
        isOpened = true
    }

    fun cerrar() {
        println("Cerrando ventana")
        isOpened = false
    }

    override fun toString(): String {
        return "Ventana(id=$id, isOpened=$isOpened)"
    }
}