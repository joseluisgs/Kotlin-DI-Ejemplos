package dagger.personas.di

import dagger.Binds
import dagger.Module
import dagger.personas.repositories.IPersonasRepository
import dagger.personas.repositories.PersonasRepository
import javax.inject.Singleton

@Module
interface PersonasRepositoryModule {
    // No devuelvo nada, solo bindeo el objeto,por eso no privider
    @Singleton
    @Binds
    fun bindPersonaRepository(impl: PersonasRepository): IPersonasRepository
}
