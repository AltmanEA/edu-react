package Component

import data.lessonsList
import data.studentsList
import react.Props
import react.RBuilder
import react.dom.h3
import react.fc
import react.router.dom.route
import react.router.dom.switch
import react.useState
import shortOutput

val CApp = fc<Props>("App") {
    val (mode, setMode) = useState("Full")
    val (marked, setMarked) = useState(lessonsList.map { it.students.map { false } })
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
    navigator()
    modePicker(mode, setMode)
    shortOutput.Provider(mode) {
        switch {
            route("/lesson",
                render = {
                    lessonsList.mapIndexed { index, lesson ->
                        h3 { +lesson.name }
                        markedList(
                            lesson.students.zip(marked[index]),
                            { onClick(index, it) },
                            RBuilder::student
                        )
                    }
                }
            )
            route("/student",
                render = {
                    studentsList.mapIndexed { indexStudent, student ->
                        h3 { +student.fullName }
                        val list = lessonsList.mapIndexedNotNull {indexLesson, lesson ->
                            val index = lesson.students.indexOf(student)
                            if (index>=0)
                                lesson to marked[indexLesson][index]
                            else
                                null
                        }
                        markedList(
                            list,
                            { onClick(it, indexStudent) },
                            RBuilder::lesson
                        )
                    }
                }
            )
        }
    }
}