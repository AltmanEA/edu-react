package component

import react.*
import react.dom.h3
import react.redux.rConnect
import react.router.dom.useParams
import redux.*
import redux.State

interface LessonStateProps : Props {
    var data: State
}

interface LessonDispatchProps : Props {
    var markStudent: (String, String) -> Unit
    var addStudent: (String, String) -> Unit
}

interface LessonProps : LessonStateProps, LessonDispatchProps

fun cLesson() = fc("Lesson") { props: LessonProps ->
    val lessonName = useParams()["name"]
    val lesson = props.data.lessons.find { it.name == lessonName }
    if (lesson == null)
        h3 { +"Lesson $lessonName not found" }
    else {
        h3 { +lesson.name }
        val stateList = props.data.getMarkedList(lesson)
        markedList(
            stateList,
            { props.markStudent(lesson.name, stateList[it].first.idName) },
            RBuilder::studentLine
        )

        val candidate = (props.data.students - lesson.students).map { it.idName }
        addElement(candidate) {
            props.addStudent(lesson.name, it)
        }
    }
}

fun lessonContainer() =
    rConnect<
            FullState,
            RAction,
            WrapperAction,
            Props,
            LessonStateProps,
            LessonDispatchProps,
            LessonProps
            >(
        mapStateToProps = { state, _ ->
            data = state.data
        },
        mapDispatchToProps = { dispatch, _ ->
            markStudent = { lesson, student ->
                dispatch(MarkStudent(lesson, student))
            }
            addStudent = { lesson, student ->
                dispatch(AddStudentToLesson(lesson, student))
            }
        }
    )(
        cLesson().unsafeCast<ComponentClass<LessonProps>>()
    )