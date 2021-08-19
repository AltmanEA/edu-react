package Component

import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.h3
import react.dom.input
import react.dom.span

external interface ModePickerProps : Props {
    var mode: String
    var setMode: StateSetter<String>
}

val CModePicker = fc("ModePicker") { props: ModePickerProps ->
    val modes = listOf("Full", "Short")
    val refs = modes.map { useRef<INPUT>() }
    val onChange: (Event) -> Unit = {
        refs
            .find { it.current?.checked ?: false }
            ?.current?.value
            ?.let {
                if (it != props.mode)
                    props.setMode(it)
            }
    }
    h3 { +"Output Mode" }
    modes.mapIndexed { index, _mode ->
        span {
            input(InputType.radio, name = "outputMode") {
                attrs.value = _mode
                ref = refs[index]
            }
            +_mode
            attrs.onClickFunction = onChange
        }
    }
}

fun RBuilder.modePicker(mode: String, setMode: StateSetter<String>) =
    child(CModePicker) {
        attrs.mode = mode
        attrs.setMode = setMode
    }