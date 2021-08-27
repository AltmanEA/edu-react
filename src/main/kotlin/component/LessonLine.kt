package component

import data.Lesson
import react.Props
import react.RBuilder
import react.dom.span
import react.fc

external interface LessonLineProps : Props {
    var lesson: Lesson
    var marked: Boolean
}

val cLessonLine = fc ("LessonLine") { props: LessonLineProps ->
    span(if (props.marked) "marked" else "unmarked") {
        +props.lesson.name
    }
}

fun RBuilder.lessonLine(lesson: Lesson, marked: Boolean) =
    child(cLessonLine) {
        attrs.lesson = lesson
        attrs.marked = marked
    }