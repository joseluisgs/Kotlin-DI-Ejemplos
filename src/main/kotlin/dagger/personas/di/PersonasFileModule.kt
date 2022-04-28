package dagger.personas.di

import dagger.Binds
import dagger.Module
import dagger.personas.services.IPersonasStorage
import dagger.personas.services.PersonasStorageFile
import javax.inject.Singleton

@Module
interface PersonasFileModule {
    @Singleton
    @Binds
    fun bindPersonaDBStorage(impl: PersonasStorageFile): IPersonasStorage
}
