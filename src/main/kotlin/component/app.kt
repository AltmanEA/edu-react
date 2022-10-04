package component

import csstype.Display
import csstype.Flex
import data.courseList
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.div
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link

val shortOutput = createContext("Full")

val app = FC<Props>("App") {
    val (mode, setMode) = useState("Full")
    val courses = courseList.map { useState(it) }
    CModePicker {
        _mode = mode
        _setMode = setMode
    }
    shortOutput.Provider(mode) {
        HashRouter {
            div {
                css {
                    display = Display.flex
                }
                courses.map {(_course, _) ->
                    val name = _course.name
                    Link {
                        css {
                            flex = Flex.minContent
                        }
                        +name
                        to = name
                    }
                }
            }
            Routes {
                    Route {
                        path = ":name"
                        element = CCoursePicker.create {
                                this.courses = courses
                        }
                    }
            }
        }
    }
}