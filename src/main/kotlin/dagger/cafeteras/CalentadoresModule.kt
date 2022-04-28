package dagger.cafeteras

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Siempre que uses @Binds, debe tener @Inject el constructor
 * de la dependencia
 */

@Module
internal interface CalentadoresModule {
    // De esta manera cuando necesitemos un calentador, inyectamos este
    // Ademas siempre el mismo objeto
    @Binds
    @Singleton
    fun bindCalentador(impl: CalentadorElectrico): Calentador
}