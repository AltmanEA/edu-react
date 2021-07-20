package Component

import data.Student
import react.*
import react.dom.*

external interface StudentProps : RProps {
    var student: Student
}

val CStudent = functionalComponent<StudentProps> { props ->
    div("student") {
        props.student.let {
            +"${it.firstname} ${it.surname}"
        }
    }
}

fun RBuilder.student(student:Student) = child(CStudent) {
    attrs.student = student
}