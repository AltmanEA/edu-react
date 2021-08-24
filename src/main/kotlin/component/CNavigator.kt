package component

import react.Props
import react.RBuilder
import react.child
import react.dom.li
import react.dom.ul
import react.fc
import react.router.dom.routeLink

val CNavigator = fc<Props>("Navigator") {
    ul {
        li { routeLink("/lesson") { +"Lesson" } }
        li { routeLink("/student") { +"Student" } }
    }
}

fun RBuilder.navigator() = child(CNavigator) { }