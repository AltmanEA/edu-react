package component

import data.Student
import react.*
import react.dom.span
import react.redux.rConnect
import redux.FullState

interface StudentLineOwnProps : Props {
    var student: Student
    var marked: Boolean
}

interface StudentLineStateProps : Props {
    var mode: String
}

interface StudentLineProps : StudentLineOwnProps, StudentLineStateProps

val cStudentLine = fc("StudentLine") { props: StudentLineProps ->
    span(if (props.marked) "marked" else "unmarked") {
        if (props.mode == "Full")
            +props.student.fullName
        else
            +props.student.shortName
    }
}

val studentLineHOC: HOC<StudentLineProps, StudentLineOwnProps> =
    rConnect<
            FullState,
            StudentLineOwnProps,
            StudentLineProps
            >(
        mapStateToProps = { state, own ->
            mode = state.mode
            student = own.student
            marked = own.marked
        }
    )

val studentLineContainer: ComponentClass<StudentLineOwnProps> =
    studentLineHOC(
        cStudentLine.unsafeCast<ComponentClass<StudentLineProps>>()
    )

fun RBuilder.studentLine(student: Student, marked: Boolean) =
    child(studentLineContainer) {
        attrs.student = student
        attrs.marked = marked
    }