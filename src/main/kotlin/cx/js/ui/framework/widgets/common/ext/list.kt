package cx.js.ui.framework.widgets.common.ext

import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody
import react.React

inline fun BulletList(
    key: String? = null,
    items: WidgetBody = {}
) = React.createElement(
    "ul",
    null,
    *elementsArray(items)
)

inline fun ListItem(
    key: String? = null,
    children: WidgetBody = { }
) = React.createElement(
    "li",
    null,
    *elementsArray(children)
)