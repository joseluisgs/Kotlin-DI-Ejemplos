package dagger.personas.services

import dagger.personas.models.Persona
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonasStorageDataBase
@Inject constructor() : IPersonasStorage {
    private val id = UUID.randomUUID().toString()

    override fun save(item: Persona): Persona {
        println("PersonaDataBaseStorage.save() --> $item")
        return item
    }

    override fun toString() = "PersonaDataBaseStorage(id='$id')"
}
