package manual.personas.controllers

import dagger.personas.models.Persona
import dagger.personas.repositories.IPersonasRepository
import java.util.*

class PersonasController(private val personaRepository: IPersonasRepository) {
    private val id = UUID.randomUUID()

    fun save(persona: Persona): Persona {
        println("PersonasController.save() --> $persona")
        return personaRepository.save(persona)
    }

    override fun toString() = "PersonaController(personaRepository=$personaRepository, id='$id')"
}
