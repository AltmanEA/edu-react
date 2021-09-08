import component.cStudentLine
import data.Student
import kotlinx.browser.document
import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import react.dom.render
import kotlin.test.Test
import kotlin.test.assertEquals

class CompTest {

    // comment fun main in main.kt !!!

    @Test
    fun studentLineTest(){
        val divElem = document.getElementById("root")
        render(divElem){
            child(cStudentLine()){
                attrs.student = Student("Test", "Student")
                attrs.marked = true
                attrs.mode = "Full"
            }
        }
        println(divElem?.innerHTML)
        val elem = divElem?.children?.get(0) as HTMLElement
        assertEquals("marked", elem.className)
        assertEquals("Test Student", elem.innerText)

        render(divElem){
            child(cStudentLine()){
                attrs.student = Student("Test", "Student")
                attrs.marked = false
                attrs.mode = "Short"
            }
        }
        println(divElem.innerHTML)

        val elem2 = divElem.children[0] as HTMLElement
        assertEquals("unmarked", elem2.className)
        assertEquals("T. Student", elem2.innerText)
    }
}