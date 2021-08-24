package component

import react.Props
import react.RBuilder
import react.dom.h3
import react.dom.li
import react.dom.ol
import react.fc
import react.router.dom.*
import react.useReducer
import redux.MarkStudent
import redux.markReducer
import redux.testState

interface NameRouterProps : RouterProps {
    var name: String
}

val CApp = fc<Props>("App") {
    val (state, dispatch) = useReducer(markReducer, testState())
    navigator()
    switch {
        route("/lesson",
            exact = true,
            render = {
                h3 { +"Lessons" }
                ol {
                    state.lessons.map { lesson ->
                        li { routeLink("/lesson/${lesson.name}") { +lesson.name } }
                    }
                }
            }
        )
        route("/lesson/:name",
            render = { routeProps: RouteResultProps<NameRouterProps> ->
                val lessonName = routeProps.match.params.name
                val lesson = state.lessons.find { it.name == lessonName }
                if (lesson == null)
                    h3 { +"Lesson $lessonName not found" }
                else {
                    h3 { +lesson.name }
                    val stateList = state.getMarkedList(lesson)
                    markedList(
                        stateList,
                        { dispatch(MarkStudent(lesson.name, stateList[it].first.idName)) },
                        RBuilder::student
                    )
                }
            }
        )
        route("/student",
            exact = true,
            render = {
                h3 { +"Students" }
                ol {
                    state.students.map { student ->
                        li { routeLink("/student/${student.idName}") { +student.fullName } }
                    }
                }
            }
        )
        route("/student/:name",
            render = { routeProps: RouteResultProps<NameRouterProps> ->
                val studentMame = routeProps.match.params.name
                val student = state.students.find { it.idName == studentMame }
                if (student == null)
                    h3 { +"Student $studentMame not found" }
                else {
                    h3 { +student.fullName }
                    val stateList = state.getMarkedList(student)
                    markedList(
                        stateList,
                        { dispatch(MarkStudent(stateList[it].first.name, student.idName))},
                        RBuilder::lesson
                    )
                }
            }
        )
    }
}

