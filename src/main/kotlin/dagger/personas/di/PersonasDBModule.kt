package dagger.personas.di

import dagger.Binds
import dagger.Module
import dagger.personas.services.IPersonasStorage
import dagger.personas.services.PersonasStorageDataBase
import javax.inject.Singleton

@Module
interface PersonasDBModule {
    @Singleton
    @Binds
    fun bindPersonaDBStorage(impl: PersonasStorageDataBase): IPersonasStorage
}
