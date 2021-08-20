import Component.CApp
import kotlinx.browser.document
import react.child
import react.createContext
import react.dom.render
import react.router.dom.hashRouter

val shortOutput = createContext("Full")

fun main() {
    render(document.getElementById("root")) {
        hashRouter {
            child(CApp)
        }
    }
}
