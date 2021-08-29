package component

import react.*
import react.dom.h3
import react.redux.rConnect
import react.router.dom.useParams
import redux.*
import redux.State

interface StudentStateProps : Props {
    var data: State
}

interface StudentDispatchProps : Props {
    var markLesson: (String, String) -> Unit
}

interface StudentProps : StudentStateProps, StudentDispatchProps

fun cStudent() = fc("Student") { props: StudentProps ->
    val studentId = useParams<NameRouterProps>()?.name
    val student = props.data.students.find { it.idName == studentId }
    if (student == null)
        h3 { +"Student $studentId not found" }
    else {
        h3 { +student.fullName }
        val stateList = props.data.getMarkedList(student)
        markedList(
            stateList,
            { props.markLesson(stateList[it].first.name, student.idName) },
            RBuilder::lessonLine
        )
    }
}

fun studentContainer() =
    rConnect<
            FullState,
            RAction,
            WrapperAction,
            Props,
            StudentStateProps,
            StudentDispatchProps,
            StudentProps
            >(
        mapStateToProps = { state, _ ->
            data = state.data
        },
        mapDispatchToProps = { dispatch, _ ->
            markLesson = { lesson, student ->
                dispatch(MarkStudent(lesson, student))
            }
        }
    )(
        cStudent().unsafeCast<ComponentClass<StudentProps>>()
    )