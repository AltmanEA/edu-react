package Component

import data.Student
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import shortOutput

external interface StudentProps : Props {
    var student: Student
    var marked: Boolean
    var onClick: (Event) -> Unit
}

val CStudent = fc ("Student") { props: StudentProps ->
    div(if (props.marked) "marked" else "unmarked") {
        if(useContext(shortOutput)=="Full")
            +props.student.fullName
        else
            +props.student.shortName
        attrs.onClickFunction = props.onClick
    }
}

fun RBuilder.student(student: Student, marked: Boolean, onClick: (Event) -> Unit) =
    child(CStudent) {
        attrs.student = student
        attrs.marked = marked
        attrs.onClick = onClick
    }