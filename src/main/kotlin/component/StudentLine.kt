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

fun cStudentLine() = fc("StudentLine") { props: StudentLineProps ->
    span(if (props.marked) "marked" else "unmarked") {
        if (props.mode == "Full")
            +props.student.fullName
        else
            +props.student.shortName
    }
}

fun studentLineContainer() =
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
    )(
        cStudentLine().unsafeCast<ComponentClass<StudentLineProps>>()
    )

fun RBuilder.studentLine(student: Student, marked: Boolean) =
    child(studentLineContainer()) {
        attrs.student = student
        attrs.marked = marked
    }