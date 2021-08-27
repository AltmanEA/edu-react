import component.CApp
import kotlinx.browser.document
import react.dom.render
import react.redux.provider
import react.router.dom.hashRouter
import redux.*

val store: Store<FullState, RAction, WrapperAction> = createStore(
    fullReducer(),
    FullState("Full", testState()),
    compose(
        rEnhancer(),
        js("if(window.__REDUX_DEVTOOLS_EXTENSION__ )window.__REDUX_DEVTOOLS_EXTENSION__ ();else(function(f){return f;});")
    )
)


fun main() {
    render(document.getElementById("root")) {
        provider(store) {
            hashRouter {
                child(CApp)
            }
        }
    }
}
