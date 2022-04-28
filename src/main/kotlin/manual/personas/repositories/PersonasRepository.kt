package manual.personas.repositories

import dagger.personas.models.Persona
import dagger.personas.repositories.IPersonasRepository
import dagger.personas.services.IPersonasStorage
import java.util.*

class PersonasRepository(private val storage: IPersonasStorage) : IPersonasRepository {
    private val id = UUID.randomUUID()

    override fun save(entity: Persona): Persona {
        println("PersonasRepository.save() -->$entity")
        return storage.save(entity)
    }

    override fun toString() = "PersonasRepository(storage=$storage, id='$id')"

}
