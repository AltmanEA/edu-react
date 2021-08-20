package data

data class Lesson(
    val name: String,
    val students: MutableList<Student> = ArrayList()
)
