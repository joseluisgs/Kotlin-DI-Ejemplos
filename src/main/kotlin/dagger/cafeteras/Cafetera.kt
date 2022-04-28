package dagger.cafeteras

import dagger.Lazy
import java.util.*
import javax.inject.Inject

// El conste de crear o enlazar una instancia de una clase que no se usa es alto
// Podemos usar Lazy para que se haga solo si al final la usamos en el resto de los métodos
// esto es la carga perezosa
class Cafetera
@Inject constructor(
    // Solo lo creamos cuando lo usemos... Carga perezosa
    // import dagger.Lazy, no el de Kotlin :)
    private val calentador: Lazy<Calentador>,
    private val bomba: Bomba
) {
    private val id = UUID.randomUUID()

    fun servir() {
        // El get es porque es Lazy, solo lo creamos cuando lo usemos la 1ª vez, por eso hay que usar el get
        calentador.get().encender()
        bomba.bombear()
        println("[_]P !Taza de Café! [_]P ")
        calentador.get().apagar()
    }

    override fun toString(): String {
        return "Cafetera(id=$id, calentador=${calentador.get()}, bomba=$bomba)"
    }
}