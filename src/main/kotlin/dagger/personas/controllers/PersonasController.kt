package dagger.personas.controllers

import dagger.personas.models.Persona
import dagger.personas.repositories.IPersonasRepository
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonasController
@Inject constructor(
    private val personaRepository: IPersonasRepository
) {
    private val id = UUID.randomUUID().toString()

    fun save(persona: Persona): Persona {
        println("PersonaController.save() --> $persona")
        return personaRepository.save(persona)
    }

    override fun toString() = "PersonaController(personaRepository=$personaRepository, id='$id')"
}
