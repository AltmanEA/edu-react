package Component

import data.Student
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.li
import react.dom.ol
import kotlin.collections.mapIndexed

interface StudentListProps : Props {
    var students: Array<Student>
}

interface StudentListState : State {
    var marked: Array<Boolean>
}

class StudentList(props: StudentListProps) :
    RComponent<StudentListProps, StudentListState>(props) {

    override fun StudentListState.init(props: StudentListProps) {
        marked = Array(props.students.size) { false }
    }

    override fun RBuilder.render() {
        ol {
            props.students.mapIndexed { index, student ->
                li {
                    student(student, state.marked[index], onClick(index))
                }
            }
        }
        div {
            val markedStudents = state.marked.sumOf { if (it) 1L else 0 }
            +"$markedStudents marked"
        }
    }

    fun onClick(index: Int): (Event) -> Unit = {
        setState {
            marked[index] = !marked[index]
        }
    }
}

fun RBuilder.studentList(students: Array<Student>) =
    child(StudentList::class) {
        attrs.students = students
    }