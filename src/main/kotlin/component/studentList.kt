package component

import csstype.FontWeight
import data.Student
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol

external interface StudentListProps : Props {
    var list: List<Student>
    var marked: List<Boolean>
    var clickItem: (Int) -> Unit
}

val CStudentList = FC<StudentListProps>("StudentList") {props ->
    ol {
        props.list.mapIndexed { index, _student ->
            li {
                CStudent {
                    student = _student
                }
                if (props.marked[index])
                    css {
                        fontWeight = FontWeight.bold
                    }
                onClick = {
                    props.clickItem(index)
                }
            }
        }
    }
}