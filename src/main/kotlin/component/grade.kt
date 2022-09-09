package component

import react.FC
import react.Props
import react.dom.html.ReactHTML.input

external interface GradeProps : Props {
    var grade: Int
    var setGrade: (Int) -> Unit
}

val CGrade = FC<GradeProps> { props ->
    val grade = if (props.grade in 2..5) props.grade.toString() else ""
    input {
        defaultValue = grade
        onChange = {
            val newGrade = it.target.value.toIntOrNull()
            if (newGrade !== null && newGrade in 2..5)
                props.setGrade(newGrade)
            else
                it.target.value = grade
        }
    }
}