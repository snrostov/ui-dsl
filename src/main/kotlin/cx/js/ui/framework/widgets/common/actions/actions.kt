package cx.js.ui.framework.widgets.common.actions

import cx.js.kotlin.buildProps
import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody
import react.React
import react.ReactElement

inline fun HRef(
    key: String? = null,
    src: ReactElement? = null,
    target: ReactElement? = null,
    body: WidgetBody = { }
): ReactElement = TODO()

inline fun Button(
    key: String? = null,
    noinline onClick: () -> Unit,
    body: WidgetBody = { }
): ReactElement = React.createElement(
    "button",
    buildProps {
      it.onClick = onClick
    },
    *elementsArray(body)
)
