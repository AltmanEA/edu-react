package Component

import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.child
import react.dom.li
import react.dom.ol
import react.fc

interface MarkedListProps<E> : Props {
    var elements: List<Pair<E, Boolean>>
    var onClick: (Int) -> Unit
    var comp: RBuilder.(E, Boolean) -> Unit
}

fun <E> CMarkedList() = fc("MarkedList") { props: MarkedListProps<E> ->
    ol {
        props.elements.mapIndexed {index, elem ->
            li {
                props.comp(this, elem.first, elem.second)
                attrs.onClickFunction = {props.onClick(index)}
            }
        }
    }
}

fun <E> RBuilder.markedList(
    elements: List<Pair<E, Boolean>>,
    onClick: (Int) -> Unit,
    comp: RBuilder.(E, Boolean) -> Unit
) =
    child(CMarkedList<E>()) {
        attrs.elements = elements
        attrs.onClick = onClick
        attrs.comp = comp
    }