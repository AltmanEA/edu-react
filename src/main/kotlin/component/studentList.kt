package component

import csstype.FontWeight
import data.studentList
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.useState

val CStudentList = FC<Props>("StudentList")  {
    var highlighted by useState<Int>()
    val grades by useState(studentList.mapTo(mutableListOf()) { 0 })
    h1 {
        +"List of student"
    }
    ol {
        studentList.mapIndexed { index, _student ->
            li {
                CStudent {
                    student = _student
                }
                if (index==highlighted)
                    css {
                        fontWeight = FontWeight.bold
                    }
                onClick = {
                    highlighted = index
                }
                CGrade {
                    grade = grades[index]
                    setGrade = {
                        grades[index] = it
                    }
                }
            }
        }
    }
    label {
        +"Total: ${grades.sum()}"
    }
}