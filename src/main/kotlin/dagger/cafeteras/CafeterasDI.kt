package dagger.cafeteras

import dagger.Component
import javax.inject.Singleton

@Singleton // Siempre es la misma instancia.
// Los módulos que vamos a usar
@Component(modules = [CalentadoresModule::class, BombasModule::class])
interface CafeterasDI {
    fun build(): Cafetera
}
