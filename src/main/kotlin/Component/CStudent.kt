package Component

import data.Student
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*

external interface StudentProps : Props {
    var student: Student
}

external interface StudentState : State {
    var marked: Boolean
}

class CStudent : RComponent<StudentProps, StudentState>(){
    init {
        state.apply {
            marked = false
        }
    }
    override fun RBuilder.render() {
        div(
            if (state.marked) "marked" else "unmarked"
        ) {
            +"${props.student.firstname} ${props.student.surname}"
            attrs.onClickFunction = {
                setState{
                    marked = !marked
                }
            }
        }
    }

}

fun RBuilder.student(student: Student) =
    child(CStudent::class) {
        attrs.student = student
    }

//val CStudent = fc { props: StudentProps ->
//    div("student") {
//        props.student.let {
//            +"${it.firstname} ${it.surname}"
//        }
//    }
//}
//
//fun RBuilder.student(student:Student) = child(CStudent) {
//    attrs.student = student
//}