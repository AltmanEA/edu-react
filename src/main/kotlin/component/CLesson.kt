package component

import data.Lesson
import react.Props
import react.RBuilder
import react.child
import react.dom.span
import react.fc

external interface LessonProps : Props {
    var lesson: Lesson
    var marked: Boolean
}

val CLesson = fc ("Lesson") { props: LessonProps ->
    span(if (props.marked) "marked" else "unmarked") {
        +props.lesson.name
    }
}

fun RBuilder.lesson(lesson: Lesson, marked: Boolean) =
    child(CLesson) {
        attrs.lesson = lesson
        attrs.marked = marked
    }