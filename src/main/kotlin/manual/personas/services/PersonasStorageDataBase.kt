package manual.personas.services

import manual.personas.models.Persona
import java.util.*

class PersonasStorageDataBase : IPersonaStorage {
    private val id = UUID.randomUUID()

    override fun save(item: Persona): Persona {
        println("PersonasStorageDataBase.save() --> $item")
        return item
    }

    override fun toString() = "PersonaDataBaseStorage(id='$id')"
}
