package redux

class MarkStudent(val lessonName: String, val studentId: String): RAction

class AddStudentToLesson(val lessonName: String, val studentId: String): RAction

class RemoveStudentFromLesson(val lessonName: String, val studentId: String): RAction