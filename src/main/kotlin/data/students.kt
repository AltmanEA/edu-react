package data

data class Student (
    val firstname: String,
    val surname: String
){
    val shortName
        get() = "${firstname[0]}. $surname"
    val fullName
        get() = "$firstname $surname"
}

val studentList =
    arrayListOf(
        Student("Sheldon", "Cooper"),
        Student("Leonard", "Hofstadter"),
        Student("Howard", "Wolowitz")
    )