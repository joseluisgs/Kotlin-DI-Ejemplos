package dagger.personas.di

import dagger.Component
import dagger.personas.controllers.PersonasController
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PersonasDBModule::class,
        PersonasRepositoryModule::class
    ]
)
interface PersonasDBController {
    fun build(): PersonasController
}
