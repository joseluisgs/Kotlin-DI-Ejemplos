package manual.myviews

import java.util.*

class MyView {
    private val id: UUID = UUID.randomUUID()
    private lateinit var presenter: Presenter

    // Aunque lo puedo fusionar arriba, imaginemos que es una actividad de android que no tiene
    // un constructor por defecto o debemos hacerlo en el onCreate()
    init {
        presenter = buildPresenter { Navigator() }
    }

    override fun toString(): String {
        return "MyView(id=$id, presenter=$presenter)"
    }
}
