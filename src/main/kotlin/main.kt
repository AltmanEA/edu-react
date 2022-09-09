import component.CStudentList
import kotlinx.browser.document
import react.Component
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("div")
    document.body!!.appendChild(container)
    createRoot(container).render(CStudentList.create())
}
