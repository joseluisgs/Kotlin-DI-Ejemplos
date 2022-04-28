package dagger.cafeteras

import java.util.*
import javax.inject.Inject

class CalentadorElectrico
@Inject constructor() : Calentador {
    private val id: UUID = UUID.randomUUID()

    // true si esta calentando, false si esta apagado
    private var calentando = false

    override fun encender() {
        calentando = true
        println("~ ~ calentando ~ ~ ~")
    }

    override fun apagar() {
        calentando = false
    }

    override fun estaCaliente(): Boolean {
        return calentando
    }

    override fun toString(): String {
        return "CalentadorElectrico(id='$id', calentando=$calentando)"
    }
}
