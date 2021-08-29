package component

import react.Props
import react.RBuilder
import react.dom.li
import react.dom.ul
import react.fc
import react.router.dom.routeLink

fun cNavigator() = fc<Props>("Navigator") {
    ul {
        li { routeLink("/lessons") { +"Lessons" } }
        li { routeLink("/students") { +"Students" } }
    }
}

fun RBuilder.navigator() = child(cNavigator()) { }