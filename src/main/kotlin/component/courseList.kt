package component

import csstype.FontWeight
import data.Course
import data.CourseId
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol

external interface CourseListProps : Props {
    var courses: Array<Course>
    var marked: Array<Boolean>
    var clickId: (CourseId) -> Unit
}

val CCourseList = FC<CourseListProps>("CourseList") {props ->
    ol {
        props.courses.forEachIndexed { index, course ->
            li {
                +course.id
                if (props.marked[index])
                    css {
                        fontWeight = FontWeight.bold
                    }
                onClick = {
                    props.clickId(course.id)
                }
            }
        }
    }
}