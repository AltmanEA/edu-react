package component

import data.Student
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface StudentProps : Props {
    var student: Student
}

val CStudent = FC<StudentProps> { props ->
    div {
        props.student.let {
            +"${it.firstname} ${it.surname}"
        }
    }
}
