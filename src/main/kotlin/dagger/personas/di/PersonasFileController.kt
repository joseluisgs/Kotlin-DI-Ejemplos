package dagger.personas.di

import dagger.Component
import dagger.personas.controllers.PersonasController
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PersonasFileModule::class,
        PersonasRepositoryModule::class
    ]
)
interface PersonasFileController {
    fun build(): PersonasController
}
