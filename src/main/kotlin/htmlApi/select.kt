package htmlApi

import kotlinx.html.SELECT
import kotlinx.html.TagConsumer

interface  HTMLOptionsCollection{
    val selectedIndex: Int
}

class SelectExt(initialAttributes : Map<String, String>, consumer : TagConsumer<*>)
    :SELECT(initialAttributes, consumer){
    val options: HTMLOptionsCollection? = null
}