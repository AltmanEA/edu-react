package component

import htmlApi.SelectExt
import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.dom.button
import react.dom.option
import react.dom.select
import react.fc
import react.useRef

interface AddElementProps : Props {
    var elements: List<String>
    var onSelect: (String) -> Unit
}

fun CAddElement() = fc("SelectElement") { props: AddElementProps ->
    val selectRef = useRef<SelectExt>()
    select {
        ref = selectRef
        props.elements.map { element ->
            option {
                attrs.value = element
                +element
            }
        }
    }
    button {
        +"Add"
        attrs.onClickFunction = {
            val select = selectRef.current
            select?.let {
                it.options?.selectedIndex?.let {
                    props.elements.getOrNull(it)?.let {
                        props.onSelect(it)
                    }
                }
            }
        }
    }
}

fun RBuilder.addElement(
    elements: List<String>,
    onSelect: (String) -> Unit
) =
    child(CAddElement()) {
        attrs.elements = elements
        attrs.onSelect = onSelect
    }