package dagger.personas.di

import dagger.personas.controllers.PersonasController

object PersonasControllerFactory {
    fun withDBStorage(): PersonasController {
        return DaggerPersonasDBController.create().build()
    }

    fun withFileStorage(): PersonasController {
        return DaggerPersonasFileController.create().build()
    }
}
