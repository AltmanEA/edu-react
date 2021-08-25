package redux

import data.Lesson
import react.RReducer

val markReducer: RReducer<State, RAction> =
    { state, action ->
        when (action) {
            is MarkStudent -> markStudentNewState(state, action)
            is AddStudentToLesson -> addStudentToLessonNewState(state, action)
            else -> state
        }
    }

private fun addStudentToLessonNewState(state: State, action: AddStudentToLesson): State {
    val student = state.students.find { it.idName == action.studentId }
    return if (student == null)
        state
    else
        State(
            state.lessons.map { lesson ->
                if (lesson.name == action.lessonName)
                    Lesson(
                        lesson.name,
                        lesson.students + student
                    )
                else
                    lesson
            },
            state.students,
            state.marked
        )
}

private fun markStudentNewState(state: State, action: MarkStudent) = State(
    state.lessons,
    state.students,
    state.marked.toMutableMap().let {
        val lesson = it[action.lessonName]
        if (lesson == null)
            it[action.lessonName] = setOf(action.studentId)
        else
            it[action.lessonName] =
                if (lesson.contains(action.studentId))
                    lesson - action.studentId
                else
                    lesson + action.studentId
        it
    }
)