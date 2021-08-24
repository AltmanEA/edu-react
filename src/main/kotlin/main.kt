import component.CApp
import kotlinx.browser.document
import react.createContext
import react.dom.render
import react.router.dom.hashRouter
import redux.testState

fun main() {
    render(document.getElementById("root")) {
        hashRouter {
                child(CApp)
        }
    }
}
