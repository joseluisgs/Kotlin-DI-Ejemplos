package dagger.cafeteras

import java.util.*
import javax.inject.Inject

data class Termosifon
@Inject constructor(private val calentador: Calentador) : Bomba {
    private val id = UUID.randomUUID()

    override fun bombear() {
        if (calentador.estaCaliente()) {
            println("=> => bombeando => =>")
        }
    }
}
