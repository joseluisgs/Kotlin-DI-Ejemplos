package dagger.myviews

import java.util.*
import javax.inject.Inject

class Presenter
@Inject constructor(navigator: Navigator) {
    private val id = UUID.randomUUID().toString()
    private val navigator: Navigator

    init {
        this.navigator = navigator
    }

    override fun toString(): String {
        return "Presenter{" +
                "id='" + id + '\'' +
                ", navigator=" + navigator +
                '}'
    }
}
