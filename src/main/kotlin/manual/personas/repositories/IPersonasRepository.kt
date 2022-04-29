package manual.personas.repositories

import manual.personas.models.Persona
import java.util.*

interface IPersonasRepository : CrudRepository<Persona, UUID>