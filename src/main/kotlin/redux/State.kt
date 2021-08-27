package redux

import data.Lesson
import data.Student
import data.lessonsList
import data.studentsList

class State(
    val lessons: List<Lesson> = emptyList(),
    val students: List<Student> = emptyList(),
    val marked: Map<String, Set<String>> = emptyMap()
) {
    fun getMarkedList(lesson: Lesson): List<Pair<Student, Boolean>> {
        val studentsSet = marked[lesson.name] ?: emptySet()
        return lesson.students.map {
            it to studentsSet.contains(it.idName)
        }
    }

    fun getMarkedList(student: Student): List<Pair<Lesson, Boolean>> =
        lessons
            .filter { it.students.contains(student) }
            .map {
                it to (
                        marked[it.name]
                            ?.contains(student.idName)
                            ?: false
                        )
            }

}

class FullState(
    val mode: String,
    val data: State
)

fun testState() =
    State(
        lessonsList.map { it },
        studentsList.map { it },
        emptyMap()
    )
