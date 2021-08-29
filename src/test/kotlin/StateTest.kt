import redux.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StateTest {

    // comment fun main in main.kt !!!

    @Test
    fun reducersTest(){
        val store = createStore(
            fullReducer(),
            FullState("Full", testState()),
            rEnhancer()
        )
        assertEquals(0, store.getState().data.marked.size)
        store.dispatch(MarkStudent("Phys", "SheldonCooper"))
        assertEquals(1, store.getState().data.marked.size)
        assertEquals(1, store.getState().data.marked["Phys"]?.size)
        assertTrue(store.getState().data.marked["Phys"]?.contains("SheldonCooper")?:false)
    }

    @Test
    fun selectorsTest(){
        val store = createStore(
            fullReducer(),
            FullState("Full", testState()),
            rEnhancer()
        )
        store.dispatch(MarkStudent("Phys", "SheldonCooper"))
        store.dispatch(MarkStudent("Math", "SheldonCooper"))
        store.dispatch(MarkStudent("Math", "LeonardHofstadter"))
        val sheldon = store.getState().data.students.find { it.firstname == "Sheldon" }!!
        val sheldonLesson = store.getState().data.getMarkedList(sheldon)
        assertEquals(3, sheldonLesson.size)
        assertEquals(2, sheldonLesson.filter { it.second }.size)
        val math = store.getState().data.lessons.find { it.name == "Math" }!!
        val mathStudent = store.getState().data.getMarkedList(math)
        assertEquals(3, mathStudent.size)
        assertEquals(2, mathStudent.filter { it.second }.size)

    }

}