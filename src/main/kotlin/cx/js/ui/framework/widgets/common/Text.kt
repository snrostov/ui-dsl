package cx.js.ui.framework.widgets.common

import cx.js.kotlin.buildProps
import cx.js.ui.framework.widgets.css.toReactProps
import cx.js.ui.framework.widgets.elements
import cx.js.ui.framework.widgets.elementsArray
import cx.ui.framework.WidgetBody
import flutter.*
import react.React
import react.ReactElement

inline fun Text(
    key: String? = null,
    text: String? = null,
    color: Color? = null,
    fontSize: Double? = null,
    fontFamily: String? = null,
    letterSpacing: Double? = null,
    wordSpacing: Double? = null,
    lineHeight: Double? = null,
    decoration: TextDecoration? = null,
    style: TextStyle? = null,
    align: TextAlign? = null,
    direction: TextDecoration? = null,
    softWrap: Boolean = false,
    overflow: TextOverflow? = null,
    textScaleFactor: Double? = 1.0,
    maxLines: Int? = null,
    body: WidgetBody = {}
): ReactElement {
  val propsStyle = buildProps<dynamic> { }
  val props = buildProps<dynamic> {
    it.style = propsStyle
  }
  if (key != null) {
    props.key = key
  }

  (style ?: TextStyle()).copy(
      color = color,
      fontSize = fontSize,
      fontFamily = fontFamily,
      letterSpacing = letterSpacing,
      wordSpacing = wordSpacing,
      lineHeight = lineHeight,
      decoration = decoration
  ).toReactProps(propsStyle)

  align?.toReactProps(propsStyle)
  direction?.toReactProps(propsStyle)
  if (softWrap) TODO()
  overflow?.toReactProps(propsStyle)
  if (textScaleFactor != 1.0) TODO()
  if (maxLines != null) TODO()

  return React.createElement(
      "span",
      props,
      *elementsArray {
        if (text != null) {
          require(elements(body) == null) { "When text is provided body should be empty" }
          +text
        } else {
          +body
        }
      }
  )
}