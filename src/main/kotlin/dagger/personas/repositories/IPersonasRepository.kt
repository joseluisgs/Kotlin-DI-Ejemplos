package dagger.personas.repositories

import dagger.personas.models.Persona
import java.util.*

interface IPersonasRepository : CrudRepository<Persona, UUID>