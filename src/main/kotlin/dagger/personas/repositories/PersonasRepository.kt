package dagger.personas.repositories

import dagger.personas.models.Persona
import dagger.personas.services.IPersonasStorage
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * El repositorio almacena los datos de las personas.
 */
@Singleton
class PersonasRepository
@Inject constructor(
    private val storage: IPersonasStorage
) : IPersonasRepository {
    private val id = UUID.randomUUID().toString()

    override fun save(entity: Persona): Persona {
        println("PersonaRepository.save() -->$entity")
        return storage.save(entity)
    }

    override fun toString() = "PersonaRepository(storage=$storage, id='$id')"
}
