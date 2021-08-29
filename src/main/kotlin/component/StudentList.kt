package component

import data.Student
import react.*
import react.dom.h3
import react.dom.li
import react.dom.ol
import react.redux.rConnect
import react.router.dom.routeLink
import redux.FullState

interface StudentListProps : Props {
    var students: List<Student>
}

fun cStudentList() = fc("StudentList") { props: StudentListProps ->
    h3 { +"Students" }
    ol {
        props.students.map { student ->
            val name = student.idName
            li { routeLink("/students/$name") { +name } }
        }
    }
}

fun studentListContainer() =
    rConnect<
            FullState,
            Props,
            StudentListProps
            >(
        mapStateToProps = { state, _ ->
            students = state.data.students
        }
    )(
        cStudentList().unsafeCast<ComponentClass<StudentListProps>>()
    )
