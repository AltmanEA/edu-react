package Component

import react.Props
import react.RBuilder
import react.dom.h3
import react.fc
import react.useState
import shortOutput

val CApp = fc<Props>("App") {
    val (mode, setMode) = useState("Full")
    val (marked, setMarked) = useState(data.lessonsList.map { it.students.map { false } })
    fun onClick(indexLesson: Int, indexStudent: Int) {
        setMarked {
            it.mapIndexed { _indexLesson, lesson ->
                if (indexLesson == _indexLesson)
                    lesson.mapIndexed { _indexStudent, student ->
                        if (indexStudent == _indexStudent)
                            !student
                        else
                            student
                    }
                else
                    lesson
            }
        }
    }
    modePicker(mode, setMode)
    shortOutput.Provider(mode) {
        data.lessonsList.mapIndexed { index, lesson ->
            h3 { +lesson.name }
            markedList(
                lesson.students.zip(marked[index]),
                { onClick(index, it) },
                RBuilder::student
            )
        }
    }
}