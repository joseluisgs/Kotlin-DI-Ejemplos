package dagger.personas

import dagger.personas.di.PersonasControllerFactory
import dagger.personas.models.Persona

fun main() {
    println("Personas: Model->Controller->Repository->Storage(Database|File)")
    println("===============================================================")
    val p = Persona(nombre = "Juan", apellido = "Perez", dni = "12345678")
    println(p)
    println()
    val contRepoStorageBD = PersonasControllerFactory.withDBStorage()
    println(contRepoStorageBD)
    val contRepoStorageFile = PersonasControllerFactory.withFileStorage()
    println(contRepoStorageFile)
    val resBD = contRepoStorageBD.save(p)
    println("Resultado BD: $resBD")
    val resFile = contRepoStorageFile.save(p)
    println("Resultado File: $resFile")
    println()
}