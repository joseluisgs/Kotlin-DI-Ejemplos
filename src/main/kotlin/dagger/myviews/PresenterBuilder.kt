package dagger.myviews

import dagger.Component


@Component
interface PresenterBuilder {
    fun inject(myClass: MyView?)
}
