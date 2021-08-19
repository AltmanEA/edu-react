package Component

import react.Props
import react.dom.h1
import react.fc
import react.useState
import shortOutput

val CApp = fc<Props>("App"){
    val (mode, setMode) = useState("Full")
    modePicker(mode, setMode)
    shortOutput.Provider(mode){
        h1 {
            +"List of student"
        }
        studentList(data.studentList.toTypedArray())
    }
}