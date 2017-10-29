package cx.js.ui.framework.widgets.common.layout

import cx.js.kotlin.buildProps
import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody
import flutter.*
import react.React
import react.ReactElement

inline fun Row(
    key: String? = null,
    align: MainAxisAlignment = MainAxisAlignment.start,
    size: MainAxisSize = MainAxisSize.min,
    vertical: CrossAxisAlignment = CrossAxisAlignment.start,
    textDirection: TextDirection = TextDirection.ltr,
    verticalDirection: VerticalDirection = VerticalDirection.down,
    textBaseline: TextBaseline = TextBaseline.alphabetic,
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