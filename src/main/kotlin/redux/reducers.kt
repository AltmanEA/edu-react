package redux

import react.RReducer

val markReducer: RReducer<State, RAction> =
    { state, action ->
        when (action) {
            is MarkStudent ->
                State(
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
            else -> state
        }
    }