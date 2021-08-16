package Component

import data.Student
import hoc.withDisplayNameRP
import kotlinx.browser.document
import org.w3c.dom.events.Event
import react.*
import react.dom.div
import react.dom.li
import react.dom.ol
import kotlin.collections.mapIndexed

interface StudentListProps : Props {
    var students: Array<Student>
}

val CStudentList = fc { props: StudentListProps ->
    val (marked, setMarked) = useState(props.students.map { false })
    val markedStudents = marked.sumOf { if (it) 1L else 0 }
    useEffect {
        document.title = "$markedStudents marked"
    }
    fun onClick(index: Int): (Event) -> Unit = {
        setMarked{
            it.mapIndexed {_index, mark ->
                if (index==_index)
                    !mark
                else
                    mark
            }
        }
    }
    ol {
        props.students.mapIndexed { index, student ->
            li {
                student(student, marked[index], onClick(index))
            }
        }
    }
    div {
        +"$markedStudents marked"
    }
}
fun RBuilder.studentList(students: Array<Student>) =
    child(withDisplayNameRP("StudentList", CStudentList)) {
        attrs.students = students
    }
