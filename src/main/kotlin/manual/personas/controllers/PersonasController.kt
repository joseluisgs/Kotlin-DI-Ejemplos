package manual.personas.controllers

import manual.personas.models.Persona
import manual.personas.repositories.IPersonasRepository
import java.util.*

class PersonasController(private val personaRepository: IPersonasRepository) {
    private val id = UUID.randomUUID()

    fun save(persona: Persona): Persona {
        println("PersonaController.save() --> $persona")
        return personaRepository.save(persona)
    }

    override fun toString() = "PersonaController(personaRepository=$personaRepository, id='$id')"
}
