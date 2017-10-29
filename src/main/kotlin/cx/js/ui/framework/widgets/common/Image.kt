package cx.js.ui.framework.widgets.common

import cx.js.kotlin.buildProps
import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody
import react.React
import react.ReactElement

inline fun Image(
    key: String,
    body: WidgetBody = { }
): ReactElement {
  val propsStyle = buildProps<dynamic> { }
  val props = buildProps<dynamic> {
    it.style = propsStyle
  }

  return React.createElement(
      "div",
      props,
      *elementsArray(body)
  )
}