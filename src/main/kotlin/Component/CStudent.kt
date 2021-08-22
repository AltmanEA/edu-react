package Component

import data.Student
import react.*
import react.dom.span
import shortOutput

external interface StudentProps : Props {
    var student: Student
    var marked: Boolean
}

val CStudent = fc ("Student") { props: StudentProps ->
    span(if (props.marked) "marked" else "unmarked") {
            +props.student.fullName
    }
}

fun RBuilder.student(student: Student, marked: Boolean) =
    child(CStudent) {
        attrs.student = student
        attrs.marked = marked
    }