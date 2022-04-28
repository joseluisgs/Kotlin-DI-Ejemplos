package manual.myviews


fun buildPresenter(builder: () -> Navigator): Presenter {
    return Presenter(builder())
}
