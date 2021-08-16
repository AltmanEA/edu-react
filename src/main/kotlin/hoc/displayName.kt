package hoc

import kotlinext.js.Object
import kotlinext.js.js
import react.FC
import react.Props
import react.child
import react.fc

// Render props example
fun <P : Props> withDisplayNameRP(
    name: String, fComp: FC<P>
): FC<P> =
    fc(name) {
        child(fComp, it)
    }

// High-order component example
fun <P : Props> withDisplayNameHOC(
    name: String, fComp: FC<P>
): FC<P> =
    Object.assign(fComp, js {
        displayName = name
    })
