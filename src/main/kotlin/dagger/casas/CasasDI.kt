package dagger.casas

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component // (modules = VehiclesModule.class) // Modulo donde obtendremos las cosas.
interface CasasDI {
    // Cuando llamemos a buildCar, nos devolverá un objeto Car con las dependencias que nosotros
    // Le hemos indicados en el Módulo.
    fun build(): Casa
}
