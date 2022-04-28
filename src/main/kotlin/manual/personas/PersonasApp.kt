package manual.personas

import dagger.personas.controllers.PersonasController
import dagger.personas.models.Persona
import dagger.personas.repositories.PersonasRepository
import dagger.personas.services.PersonasStorageDataBase
import dagger.personas.services.PersonasStorageFile

fun main() {
    println("Personas: Model->Controller->Repository->Storage(Database|File)")
    println("==============================")
    val p = Persona(nombre = "Juan", apellido = "Perez", dni = "12345678")
    println(p)
    println()

    var contRepoStorageBD = PersonasController(PersonasRepository(PersonasStorageDataBase()))
    println(contRepoStorageBD)
    var resBD = contRepoStorageBD.save(p)
    println("Resultado BD: $resBD")

    contRepoStorageBD = PersonasController(PersonasRepository(PersonasStorageFile()))
    println(contRepoStorageBD)
    resBD = contRepoStorageBD.save(p)
    println("Resultado File: $resBD")

    println()
}