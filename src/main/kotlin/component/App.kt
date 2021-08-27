package component

import react.Props
import react.fc
import react.router.dom.RouterProps
import react.router.dom.route
import react.router.dom.switch

interface NameRouterProps : RouterProps {
    var name: String
}

val CApp = fc<Props>("App") {
    modePickerContainer {}
    navigator()
    switch {
        route("/lessons",
            exact = true,
            render = { lessonListContainer {} }
        )
        route("/lessons/:name",
            render = { lessonContainer {} }
        )
        route("/students",
            exact = true,
            render = { studentListContainer {} }
        )
        route("/students/:name",
            render = { studentContainer {} }
        )
    }
}

