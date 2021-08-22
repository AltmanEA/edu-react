package Component

import data.lessonsList
import data.studentsList
import react.Props
import react.RBuilder
import react.dom.h3
import react.dom.li
import react.dom.ol
import react.fc
import react.router.dom.*
import react.useState
import shortOutput

interface NameRouterProps : RouterProps {
    var name: String
}


val CApp = fc<Props>("App") {
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
    switch {
        route("/lesson",
            exact = true,
            render = {
                h3 { +"Lessons" }
                ol {
                    lessonsList.map { lesson ->
                        li { routeLink("/lesson/${lesson.name}") { +lesson.name } }
                    }
                }
            }
        )
        route("/lesson/:name",
            render = { routeProps: RouteResultProps<NameRouterProps> ->
                val lessonName = routeProps.match.params.name
                val index = lessonsList.indexOfFirst { it.name == lessonName }
                val lesson = lessonsList.getOrNull(index)
                if (lesson == null)
                    h3 { +"Lesson $lessonName not found" }
                else {
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
            exact = true,
            render = {
                h3 { +"Students" }
                ol {
                    studentsList.map { student ->
                        li { routeLink("/student/${student.idName}") { +student.fullName } }
                    }
                }
            }
        )
        route("/student/:name",
            render = { routeProps: RouteResultProps<NameRouterProps> ->
                val studentMame = routeProps.match.params.name
                val indexStudent = studentsList.indexOfFirst { it.idName == studentMame }
                val student = studentsList.getOrNull(indexStudent)
                if (student == null)
                    h3 { +"Student $studentMame not found" }
                else {
                    h3 { +student.fullName }
                    val lessonStudent = lessonsList.mapIndexedNotNull { indexLesson, lesson ->
                        val students = lesson.students
                        val index = students.indexOf(student)
                        if (index >= 0)
                            indexLesson to index
                        else
                            null
                    }
                    markedList(
                        lessonStudent.map { lessonsList[it.first] to marked[it.first][it.second] },
                        { onClick(lessonStudent[it].first, lessonStudent[it].second) },
                        RBuilder::lesson
                    )
                }
            }
        )
    }
}