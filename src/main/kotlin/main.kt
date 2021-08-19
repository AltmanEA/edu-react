import Component.CApp
import kotlinx.browser.document
import react.child
import react.createContext
import react.dom.render

val shortOutput = createContext("Full")

fun main() {
    render(document.getElementById("root")) {
        child(CApp)
    }
}
