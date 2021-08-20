package Component

import react.Props
import react.RBuilder
import react.dom.h1
import react.fc
import react.useState
import shortOutput

val CApp = fc<Props>("App"){
    val (mode, setMode) = useState("Full")
    val (marked, setMarked) = useState(data.studentList.map { false })
    val elements = data.studentList.zip(marked)
    fun onClick(index: Int){
        setMarked{
            it.mapIndexed {_index, mark ->
                if (index==_index)
                    !mark
                else
                    mark
            }
        }
    }
    modePicker(mode, setMode)
    shortOutput.Provider(mode){
        h1 {
            +"List of student"
        }
        markedList(elements, ::onClick, RBuilder::student)
//        studentList(data.studentList.toTypedArray())
    }
}