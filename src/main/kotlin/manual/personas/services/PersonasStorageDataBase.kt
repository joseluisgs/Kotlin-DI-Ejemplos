package manual.personas.services

import dagger.personas.models.Persona
import dagger.personas.services.IPersonasStorage
import java.util.*

class PersonasStorageDataBase : IPersonasStorage {
    private val id = UUID.randomUUID()

    override fun save(item: Persona): Persona {
        println("PersonasStorageDataBase.save() --> $item")
        return item
    }

    override fun toString() = "PersonaDataBaseStorage(id='$id')"
}
