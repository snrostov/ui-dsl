package react

import cx.js.kotlin.dynamicObj
import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody

val jsEmptyObj = dynamicObj()

inline fun element(type: String, props: dynamic = jsEmptyObj, children: WidgetBody = {})
    = React.createElement(type, props, *elementsArray(children))