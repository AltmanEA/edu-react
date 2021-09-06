package component

import data.Lesson
import react.*
import react.dom.h3
import react.dom.li
import react.dom.ol
import react.redux.rConnect
import react.router.dom.Link
import react.router.dom.routeLink
import redux.FullState

interface LessonListProps : Props {
    var lessons: List<Lesson>
}

fun cLessonList() = fc("LessonList") { props: LessonListProps ->
    h3 { +"Lessons" }
    ol {
        props.lessons.map { lesson ->
            val name = lesson.name
            li {
                Link {
                    attrs.to = "/lessons/$name"
                    +name
                }
            }
        }
    }
}

fun lessonListContainer() =
    rConnect<
            FullState,
            Props,
            LessonListProps
            >(
        mapStateToProps = { state, _ ->
            lessons = state.data.lessons
        }
    )(
        cLessonList().unsafeCast<ComponentClass<LessonListProps>>()
    )