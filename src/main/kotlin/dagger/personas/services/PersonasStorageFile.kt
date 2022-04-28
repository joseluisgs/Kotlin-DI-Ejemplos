package dagger.personas.services

import dagger.personas.models.Persona
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonasStorageFile
@Inject constructor() : IPersonasStorage {
    private val id = UUID.randomUUID().toString()
    override fun save(item: Persona): Persona {
        println("PersonaFileStorage.save() --> $item")
        return item
    }

    override fun toString() = "PersonaFileStorage(id='$id')"
}
