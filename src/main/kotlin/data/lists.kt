package data

val studentsList = listOf(
    Student("Sheldon", "Cooper"),
    Student("Leonard", "Hofstadter"),
    Student("Howard", "Wolowitz"),
    Student("Penny", "Hofstadter"),
)

val lessonsList = listOf(
    Lesson("Math", listOf(0, 1, 2).map { studentsList[it] }.toMutableList()),
    Lesson("Phys", listOf(0, 1).map { studentsList[it] }.toMutableList()),
    Lesson("Story", listOf(0, 1, 3).map { studentsList[it] }.toMutableList())
)