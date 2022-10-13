import data.Course
import data.CourseId
import data.Student
import data.StudentId
import redux.RAction

open class CourseAction: RAction
open class StudentAction: RAction
open class ModeAction: RAction

class MarkStudent(
    val courseId: CourseId,
    val studentId: StudentId
) : CourseAction()

class AddStudent(
    val student: Student
): StudentAction()

class AddCourse(
    val course: Course
): CourseAction()

class AddStudentToCourse(
    val courseId: CourseId,
    val studentId: StudentId
): CourseAction()

class ChangeMode() : ModeAction()