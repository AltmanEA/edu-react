import Component.student
import data.studentList
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.h1
import react.dom.li
import react.dom.ol
import react.dom.render

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            h1{
                +"List of student"
            }
            ol {
                studentList.map {
                    li{ student(it)}
                }
            }
        }
    }
}
