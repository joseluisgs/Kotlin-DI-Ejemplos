package dagger.myviews

import java.util.*
import javax.inject.Inject

class Navigator
@Inject constructor() {
    private val id = UUID.randomUUID().toString()
    override fun toString(): String {
        return "Navigator{" +
                "id='" + id + '\'' +
                '}'
    }
}