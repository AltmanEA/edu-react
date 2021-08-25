package data

data class Lesson(
    val name: String,
    val students: List<Student> = ArrayList()
)
