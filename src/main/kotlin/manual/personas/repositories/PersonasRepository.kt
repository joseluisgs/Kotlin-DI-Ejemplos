package manual.personas.repositories

import manual.personas.models.Persona
import manual.personas.services.IPersonaStorage
import java.util.*

class PersonasRepository(private val storage: IPersonaStorage) : IPersonasRepository {
    private val id = UUID.randomUUID()

    override fun save(entity: Persona): Persona {
        println("PersonasRepository.save() -->$entity")
        return storage.save(entity)
    }

    override fun toString() = "PersonasRepository(storage=$storage, id='$id')"

}
