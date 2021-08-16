package Component

import data.Student
import hoc.withDisplayNameHOC
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*

external interface StudentProps : Props {
    var student: Student
    var marked: Boolean
    var onClick: (Event) -> Unit
}

val CStudent = fc { props: StudentProps ->
    div(if (props.marked) "marked" else "unmarked") {
        +"${props.student.firstname} ${props.student.surname}"
        attrs.onClickFunction = props.onClick
    }
}

fun RBuilder.student(student: Student, marked: Boolean, onClick: (Event) -> Unit) =
    child(withDisplayNameHOC("Student", CStudent)) {
        attrs.student = student
        attrs.marked = marked
        attrs.onClick = onClick
    }