package component

import react.Props
import react.fc
import react.router.dom.*

interface NameRouterProps : RouterProps {
    var name: String
}

fun cApp() = fc<Props>("App") {
    (modePickerContainer()) {}
    navigator()
    Switch {
        Route {
            attrs.path = arrayOf("/lessons")
            attrs.exact = true
            (lessonListContainer()) {}
        }
        Route{
            attrs.path = arrayOf("/lessons/:name")
            (lessonContainer()) {}
        }
        Route {
            attrs.path = arrayOf("/students")
            attrs.exact = true
            (studentListContainer()) {}
        }
        Route {
            attrs.path = arrayOf("/students/:name")
            (studentContainer()) {}
        }
    }
}

