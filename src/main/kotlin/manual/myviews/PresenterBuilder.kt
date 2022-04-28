package manual.myviews

// Aceptamos cualquier función que nos devuleva un navigator
fun buildPresenter(builder: () -> Navigator): Presenter {
    return Presenter(builder())
}
