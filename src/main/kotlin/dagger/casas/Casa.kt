package dagger.casas

import java.util.*
import javax.inject.Inject

data class Casa
@Inject constructor(
    private val ventana: Ventana,
    private val puerta: Puerta,
) {
    val id: UUID = UUID.randomUUID()

    fun entrar() {
        puerta.abrir()
        puerta.cerrar()
    }

    fun ventilar() {
        ventana.abrir()
    }
}
