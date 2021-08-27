package redux

class MarkStudent(val lessonName: String, val studentId: String): RAction

class AddStudentToLesson(val lessonName: String, val studentId: String): RAction

class SetMode(val newMode: String):RAction