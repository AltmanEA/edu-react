package component

import kotlinx.html.INPUT
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.input
import react.dom.span
import react.redux.rConnect
import redux.*

interface ModePickerStateProps : Props {
    var mode: String
}

interface ModePickerDispatchProps : Props {
    var setMode: (String) -> Unit
}

interface ModePickerProps : ModePickerStateProps, ModePickerDispatchProps

fun cModePicker() = fc("ModePicker") { props: ModePickerProps ->
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
    span { +"Output Mode:" }
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

fun modePickerContainer() =
    rConnect<
            FullState,
            RAction,
            WrapperAction,
            Props,
            ModePickerStateProps,
            ModePickerDispatchProps,
            ModePickerProps
            >(
        mapStateToProps = { state, _ ->
            mode = state.mode
        },
        mapDispatchToProps = { dispatch, _ ->
            setMode = {
                dispatch(SetMode(it))
            }
        }
    )(
        cModePicker().unsafeCast<ComponentClass<ModePickerProps>>()
    )

