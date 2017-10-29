package cx.js.ui.framework.widgets.common.actions

import cx.js.kotlin.buildProps
import cx.ui.framework.WidgetBody
import react.ReactElement
import react.element

inline fun HRef(
    key: String? = null,
    src: ReactElement? = null,
    target: ReactElement? = null,
    body: WidgetBody = { }
): ReactElement = TODO()

inline fun Button(
    key: String? = null,
    disabled: Boolean? = null,
    noinline onClick: () -> Unit = { },
    body: WidgetBody = { }
): ReactElement = element(
    "button",
    buildProps {
      it.onClick = onClick
      it.disabled = disabled
    },
    body
)
